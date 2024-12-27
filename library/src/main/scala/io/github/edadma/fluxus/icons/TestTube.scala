package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/test-tube", JSImport.Default)
private object TestTubeIcon extends js.Array[js.Any]

def TestTube(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TestTubeIcon, color, size)
