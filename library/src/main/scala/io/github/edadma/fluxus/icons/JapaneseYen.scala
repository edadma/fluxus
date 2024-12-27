package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/japanese-yen", JSImport.Default)
private object JapaneseYenIcon extends js.Array[js.Any]

def JapaneseYen(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(JapaneseYenIcon, color, size)
