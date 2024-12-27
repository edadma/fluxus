package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/music-2", JSImport.Default)
private object Music2Icon extends js.Array[js.Any]

def Music2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Music2Icon, color, size)
