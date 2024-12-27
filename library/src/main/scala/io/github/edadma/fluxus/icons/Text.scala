package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/text", JSImport.Default)
private object TextIcon extends js.Array[js.Any]

def Text(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TextIcon, color, size)
