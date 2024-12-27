package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/test-tubes", JSImport.Default)
private object TestTubesIcon extends js.Array[js.Any]

def TestTubes(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TestTubesIcon, color, size)
