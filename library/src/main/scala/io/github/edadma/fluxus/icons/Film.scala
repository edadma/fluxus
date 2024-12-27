package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/film", JSImport.Default)
private object FilmIcon extends js.Array[js.Any]

def Film(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FilmIcon, color, size)
