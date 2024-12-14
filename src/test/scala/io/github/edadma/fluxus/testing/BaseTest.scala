package io.github.edadma.fluxus.testing

import io.github.edadma.logger.{FileHandler, LogLevel}
import io.github.edadma.fluxus.logger
import org.scalatest
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.BeforeAndAfterEach

trait BaseTest extends AnyFlatSpec with Matchers with BeforeAndAfterEach {
  override def beforeEach(): Unit = {
    super.beforeEach()

    logger.setLogLevel(LogLevel.OFF)
    logger.resetOpId()
  }

  def withDebugLogging(testName: String)(test: => Unit): Unit = {
    logger.setLogLevel(LogLevel.DEBUG)
    logger.setHandler(new FileHandler("log"))
    logger.debug(s"=== Starting Test: $testName ===", category = "Test")

    try {
      test
    } finally {
      logger.setLogLevel(LogLevel.OFF)
    }
  }
}
