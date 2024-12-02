package io.github.edadma.fluxus.core.debug

import io.github.edadma.fluxus.logging.Logger
import io.github.edadma.fluxus.logging.Logger.Category

case class RenderInfo(
    componentType: String,
    props: Any,
    state: Vector[Any],
    duration: Long,
    depth: Int,
)

object DebugTools {
  private var renderHistory = Map[String, Vector[RenderInfo]]()

  def recordRender(instanceId: String, info: RenderInfo, opId: Int): Unit = {
    Logger.debug(
      Category.Render,
      "Recording render info",
      opId,
      Map(
        "instanceId"    -> instanceId,
        "componentType" -> info.componentType,
        "duration"      -> info.duration,
        "depth"         -> info.depth,
      ),
    )

    renderHistory = renderHistory.updated(
      instanceId,
      renderHistory.getOrElse(instanceId, Vector.empty) :+ info,
    )
  }

  def getComponentRenderHistory(instanceId: String): Vector[RenderInfo] =
    renderHistory.getOrElse(instanceId, Vector.empty)

  def clearHistory(): Unit = renderHistory = Map.empty
}
