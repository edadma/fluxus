package io.github.edadma.fluxus.core.context

import io.github.edadma.fluxus.logging.Logger
import io.github.edadma.fluxus.logging.Logger.Category

object FrameworkConfig {
  private var currentConfig = FrameworkConfig()

  def current: FrameworkConfig = currentConfig

  def update(config: FrameworkConfig): Unit = {
    val opId = Logger.nextOperationId
    Logger.debug(
      Category.Component,
      "Updating framework configuration",
      opId,
      Map(
        "debugMode"     -> config.debugMode,
        "maxTreeDepth"  -> config.maxTreeDepth,
        "renderTimeout" -> config.renderTimeout,
      ),
    )
    currentConfig = config
  }
}

case class FrameworkConfig(
    // Debug options
    debugMode: Boolean = false,
    verboseLogging: Boolean = false,
    trackResourceMetrics: Boolean = true,
    preserveErrorStack: Boolean = true,
    validateProps: Boolean = true,
    validateHookCalls: Boolean = true,
    trackRenderTiming: Boolean = true,

    // Performance settings
    asyncRendering: Boolean = false,
    maxUpdateDepth: Int = 64,
    renderChunkSize: Int = 1000,
    deferTimeout: Int = 100,
    throttleUpdates: Boolean = false,
    memoizeProps: Boolean = true,

    // Timeouts (milliseconds)
    renderTimeout: Int = 5000,
    effectTimeout: Int = 2000,
    cleanupTimeout: Int = 2000,
    batchTimeout: Int = 50,
    deferredTimeout: Int = 100,
    idleTimeout: Int = 16,

    // Resource limits
    maxComponents: Int = 10000,
    maxDOMNodes: Int = 50000,
    maxEventListeners: Int = 10000,
    maxTreeDepth: Int = 64,
    maxHooksPerComponent: Int = 100,
    maxEffectsPerComponent: Int = 50,
    maxBatchSize: Int = 100,
) {
  def validate(): Boolean = {
    val opId  = Logger.nextOperationId
    var valid = true

    def check(cond: Boolean, msg: String): Unit = {
      if (!cond) {
        Logger.error(Category.Component, msg, opId)
        valid = false
      }
    }

    // Validate timeouts
    check(renderTimeout >= 1000, "Render timeout must be >= 1000ms")
    check(effectTimeout >= 100, "Effect timeout must be >= 100ms")
    check(cleanupTimeout >= 100, "Cleanup timeout must be >= 100ms")
    check(batchTimeout <= 100, "Batch timeout must be <= 100ms")

    // Validate resource limits
    check(maxTreeDepth >= 1 && maxTreeDepth <= 100, "Tree depth must be between 1 and 100")
    check(maxComponents > 0, "Max components must be positive")
    check(maxDOMNodes > 0, "Max DOM nodes must be positive")
    check(maxEventListeners > 0, "Max event listeners must be positive")
    check(maxHooksPerComponent > 0, "Max hooks per component must be positive")
    check(maxEffectsPerComponent > 0, "Max effects per component must be positive")
    check(maxBatchSize > 0, "Max batch size must be positive")

    // Validate performance settings
    check(maxUpdateDepth > 0, "Max update depth must be positive")
    check(renderChunkSize > 0, "Render chunk size must be positive")
    check(deferTimeout > 0, "Defer timeout must be positive")

    valid
  }
}
