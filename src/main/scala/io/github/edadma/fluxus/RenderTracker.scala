package io.github.edadma.fluxus

import scala.scalajs.js

object RenderTracker:
  private var renderCount           = 0
  private var updateCount           = 0
  private var componentRenderCounts = scala.collection.mutable.Map[String, Int]()
  private val renderTimes           = scala.collection.mutable.Queue[Double]()
  private val startTime             = js.Date.now()

  def trackRender(componentName: String): FluxusNode => FluxusNode = { node =>
    renderCount += 1
    componentRenderCounts(componentName) = componentRenderCounts.getOrElse(componentName, 0) + 1
    val now = js.Date.now()
    renderTimes.enqueue(now)

    // Keep only last 100 render times
    while (renderTimes.size > 100) renderTimes.dequeue()

    val timeSinceStart = now - startTime
    val renderRate     = if (timeSinceStart > 0) (renderCount.toDouble * 1000 / timeSinceStart) else 0

    FluxusLogger.Render.stats(
      renderCount,
      Map(
        "component" -> componentName,
        "renders"   -> componentRenderCounts(componentName),
        "rate"      -> f"$renderRate%.2f/s",
        "avgInterval" -> {
          if (renderTimes.size > 1)
            f"${renderTimes.sliding(2).map(w => w(1) - w(0)).sum / (renderTimes.size - 1)}%.2f ms"
          else "N/A"
        },
      ),
    )

    FluxusLogger.Memory.report()
    node
  }

  def logStateUpdate(): Unit =
    updateCount += 1
    FluxusLogger.State.update("StateTracker", s"Update #$updateCount")
