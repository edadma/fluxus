package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/trello", JSImport.Default)
private object TrelloIcon extends js.Array[js.Any]

def Trello(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TrelloIcon, color, size)
