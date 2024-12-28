package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/album", JSImport.Default)
private object AlbumIcon extends js.Array[js.Any]

def Album(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlbumIcon, color, size)