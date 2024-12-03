package io.github.edadma.fluxus.testing

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.BeforeAndAfterEach
import io.github.edadma.fluxus.logging.Logger
import io.github.edadma.fluxus.logging.Logger.LogLevel

trait BaseTest extends AnyFlatSpec with Matchers with BeforeAndAfterEach {
  override def beforeEach(): Unit = {
    Logger.setLevel(LogLevel.OFF)
  }

  def withDebugLogging(test: => Unit): Unit = {
    Logger.setLevel(LogLevel.DEBUG)
    try {
      test
    } finally {
      Logger.setLevel(LogLevel.OFF)
    }
  }
}
