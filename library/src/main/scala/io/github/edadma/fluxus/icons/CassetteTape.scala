package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cassette-tape", JSImport.Default)
private object CassetteTapeIcon extends js.Array[js.Any]

def CassetteTape(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CassetteTapeIcon, color, size)
