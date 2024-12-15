package io.github.edadma.fluxus.testing

import io.github.edadma.logger.{FileHandler, LogLevel}
import io.github.edadma.fluxus.logger
import org.scalajs.dom
import org.scalatest
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfterEach, Suite}

import scala.scalajs.js

trait BaseTest extends /*AnyFlatSpec with Matchers with*/ BeforeAndAfterEach { this: Suite =>
  override def beforeEach(): Unit = {
    super.beforeEach()

    logger.setLogLevel(LogLevel.OFF)
    logger.resetOpId()
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
