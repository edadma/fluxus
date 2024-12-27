package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/smartphone", JSImport.Default)
private object SmartphoneIcon extends js.Array[js.Any]

def Smartphone(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SmartphoneIcon, color, size)
