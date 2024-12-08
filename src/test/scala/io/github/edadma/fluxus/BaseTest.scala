package io.github.edadma.fluxus

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.BeforeAndAfterEach
import io.github.edadma.fluxus.logging.Logger
import io.github.edadma.fluxus.logging.Logger.LogLevel

trait BaseTest extends AnyFlatSpec with Matchers with BeforeAndAfterEach {
  override def beforeEach(): Unit = {
    super.beforeEach()

//    Logger.setLevel(LogLevel.OFF)
//    Logger.resetOperationId()
//    Hooks.clearCurrentInstance()
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
