package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/thumbs-up", JSImport.Default)
private object ThumbsUpIcon extends js.Array[js.Any]

def ThumbsUp(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ThumbsUpIcon, color, size)
