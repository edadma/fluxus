package io.github.edadma.fluxus.testing

import io.github.edadma.logger.{FileHandler, LogLevel}
import io.github.edadma.fluxus.logger
import org.scalajs.dom
import org.scalatest.exceptions.TestFailedException
import org.scalatest.{BeforeAndAfterEach, Suite}
import org.scalatest.flatspec.{AnyFlatSpec, AsyncFlatSpec}
import org.scalatest.matchers.should.Matchers
import org.scalatest.time.SpanSugar.convertIntToGrainOfTime

import scala.scalajs.js
import js.annotation.JSImport
import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.scalajs.js.timers.SetTimeoutHandle
import scala.util.Try

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

    getContainer.innerHTML = ""
  }

  def click(element: dom.Element): dom.Event = {
    val event = dom.document.createEvent("MouseEvents")

    event.asInstanceOf[js.Dynamic].initEvent("click", true, true)
    element.dispatchEvent(event)
    event
  }

  def typeInput(element: dom.Element, value: String): dom.Event = {
    val event = dom.document.createEvent("Event")

    event.asInstanceOf[js.Dynamic].initEvent("input", true, true)
    element.asInstanceOf[dom.html.Input].value = value
    element.dispatchEvent(event)
    event
  }
}

import org.scalatest.concurrent.PatienceConfiguration
import org.scalatest.time.{Span, Seconds, Millis}

trait JSEventually extends PatienceConfiguration {
  def eventually[T](block: => T)(implicit config: PatienceConfig): Future[T] = {
    val promise               = Promise[T]()
    val startTime             = System.currentTimeMillis
    val timeoutMillis         = config.timeout.toMillis
    val initialIntervalMillis = config.interval.toMillis

    def nextInterval(currentInterval: Double): Double = {
      val nextDouble = currentInterval * 2

      math.min(nextDouble, initialIntervalMillis.toDouble)
    }

    def attempt(currentInterval: Double): Unit = {
      try {
        val result = block
        promise.success(result)
      } catch {
        case e: Throwable =>
          val elapsedTime = System.currentTimeMillis - startTime

          if (elapsedTime >= timeoutMillis) {
            promise.failure(new TestFailedException(
              s"Eventually block timed out after ${timeoutMillis}ms. Last error: ${e.getMessage}",
              e,
              10,
            ))
          } else {
            js.timers.setTimeout(currentInterval) {
              attempt(nextInterval(currentInterval))
            }
          }
      }
    }

    attempt((initialIntervalMillis / 10).toDouble)
    promise.future
  }
}

// Base class for async DOM tests
class AsyncDOMSpec extends AsyncFlatSpec with DOMSpec with JSEventually {

  import org.scalajs.macrotaskexecutor.MacrotaskExecutor
  implicit override def executionContext: ExecutionContext = MacrotaskExecutor

  // Provide reasonable default patience config
  implicit override val patienceConfig: PatienceConfig = PatienceConfig(
    timeout = Span(200, Millis),
    interval = Span(50, Millis),
  )

  def withDebugLogging[T](testName: String)(test: => Future[T]): Future[T] = {
    logger.setLogLevel(LogLevel.DEBUG)
    logger.setHandler(new FileHandler("log"))
    logger.debug(s"<<<< Starting Test: $testName >>>>", category = "Test")

    test transform { result =>
      logger.setLogLevel(LogLevel.OFF)
      result
    }
  }

  def after[T](millis: Int)(block: => T): Future[T] = {
    val promise = Promise[T]()

    js.timers.setTimeout(millis) {
      try {
        val result = block
        promise.success(result)
      } catch {
        case e: Throwable =>
          promise.failure(e)
      }
    }

    promise.future
  }
}

class AnyDOMSpec extends AnyFlatSpec with DOMSpec:
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
