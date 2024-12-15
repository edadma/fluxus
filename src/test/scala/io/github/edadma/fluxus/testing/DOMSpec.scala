package io.github.edadma.fluxus.testing

import io.github.edadma.logger.{FileHandler, LogLevel}
import io.github.edadma.fluxus.logger
import org.scalajs.dom
import org.scalatest.concurrent.Eventually
import org.scalatest.{BeforeAndAfterEach, Suite}
import org.scalatest.flatspec.{AnyFlatSpec, AsyncFlatSpec}
import org.scalatest.matchers.should.Matchers
import org.scalatest.time.SpanSugar.convertIntToGrainOfTime

import scala.scalajs.js
import js.annotation.JSImport

@JSImport("jsdom", "JSDOM")
@js.native
class JSDOM(html: String) extends js.Object {
  val window: dom.Window = js.native
}

trait DOMSpec extends Matchers with BeforeAndAfterEach { this: Suite =>
  val jsdom = new JSDOM("""<!doctype html><html><body><div id="app"></div></body></html>""")

  js.Dynamic.global.global.window = jsdom.window
  js.Dynamic.global.global.document = jsdom.window.document
  js.Dynamic.global.global.Node = jsdom.window.asInstanceOf[js.Dynamic].Node

  def getContainer: dom.Element = dom.document.getElementById("app")

  override def beforeEach(): Unit = {
    super.beforeEach()

    logger.setLogLevel(LogLevel.OFF)
    logger.resetOpId()

    // Clear container before each test
    val container = getContainer
    while (container.firstChild != null) {
      container.removeChild(container.firstChild)
    }
  }

  def withDebugLogging(testName: String)(test: => Unit): Unit = {
    logger.setLogLevel(LogLevel.DEBUG)
    logger.setHandler(new FileHandler("log"))
    logger.debug(s"<<<< Starting Test: $testName >>>>", category = "Test")

    try {
      test
    } finally {
      logger.setLogLevel(LogLevel.OFF)
    }
  }

  def click(element: dom.Element): dom.Event = {
    val event = dom.document.createEvent("MouseEvents")

    event.asInstanceOf[js.Dynamic].initEvent("click", true, true)
    element.dispatchEvent(event)
    event
  }
}

// Base class for async DOM tests
class AsyncDOMSpec extends AsyncFlatSpec with Eventually with DOMSpec {
  // Configure eventually timeout
  implicit override val patienceConfig: PatienceConfig = PatienceConfig(
    timeout = scaled(1.second),
    interval = scaled(100.millis),
  )
}

class AnyDOMSpec extends AnyFlatSpec with DOMSpec
