package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/instagram", JSImport.Default)
private object InstagramIcon extends js.Array[js.Any]

def Instagram(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(InstagramIcon, color, size)
