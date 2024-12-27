package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/unlink-2", JSImport.Default)
private object Unlink2Icon extends js.Array[js.Any]

def Unlink2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Unlink2Icon, color, size)
