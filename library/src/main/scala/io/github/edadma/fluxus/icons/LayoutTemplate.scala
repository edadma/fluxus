package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/layout-template", JSImport.Default)
private object LayoutTemplateIcon extends js.Array[js.Any]

def LayoutTemplate(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LayoutTemplateIcon, color, size)
