package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/book-headphones", JSImport.Default)
private object BookHeadphonesIcon extends js.Array[js.Any]

def BookHeadphones(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BookHeadphonesIcon, color, size)
