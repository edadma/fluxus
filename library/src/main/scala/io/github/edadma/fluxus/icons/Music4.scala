package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/music-4", JSImport.Default)
private object Music4Icon extends js.Array[js.Any]

def Music4(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Music4Icon, color, size)
