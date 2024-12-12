package io.github.edadma.fluxus.testing

import io.github.edadma.logger.{LogLevel, Logger, LoggerFactory}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.BeforeAndAfterEach

trait BaseTest extends AnyFlatSpec with Matchers with BeforeAndAfterEach {
  val logger: Logger = LoggerFactory.getLogger

  override def beforeEach(): Unit = {
    super.beforeEach()

    logger.setLogLevel(LogLevel.OFF)
    logger.resetOpId()
  }

  def withDebugLogging(test: => Unit): Unit = {
    logger.setLogLevel(LogLevel.DEBUG)

    try {
      test
    } finally {
      logger.setLogLevel(LogLevel.OFF)
    }
  }
}
