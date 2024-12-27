package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/accessibility", JSImport.Default)
private object AccessibilityIcon extends js.Array[js.Any]

def Accessibility(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AccessibilityIcon, color, size)
