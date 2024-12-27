package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/workflow", JSImport.Default)
private object WorkflowIcon extends js.Array[js.Any]

def Workflow(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(WorkflowIcon, color, size)
