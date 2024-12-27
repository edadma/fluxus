package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/wallpaper", JSImport.Default)
private object WallpaperIcon extends js.Array[js.Any]

def Wallpaper(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(WallpaperIcon, color, size)
