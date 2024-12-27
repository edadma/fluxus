package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/headphones", JSImport.Default)
private object HeadphonesIcon extends js.Array[js.Any]

def Headphones(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HeadphonesIcon, color, size)
