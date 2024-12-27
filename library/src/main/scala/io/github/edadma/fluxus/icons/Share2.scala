package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/share-2", JSImport.Default)
private object Share2Icon extends js.Array[js.Any]

def Share2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Share2Icon, color, size)
