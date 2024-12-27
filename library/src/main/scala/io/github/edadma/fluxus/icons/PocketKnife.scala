package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/pocket-knife", JSImport.Default)
private object PocketKnifeIcon extends js.Array[js.Any]

def PocketKnife(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PocketKnifeIcon, color, size)
