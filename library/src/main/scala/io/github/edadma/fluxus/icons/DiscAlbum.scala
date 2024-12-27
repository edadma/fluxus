package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/disc-album", JSImport.Default)
private object DiscAlbumIcon extends js.Array[js.Any]

def DiscAlbum(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DiscAlbumIcon, color, size)
