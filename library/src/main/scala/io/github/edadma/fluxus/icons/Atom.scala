package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/atom", JSImport.Default)
private object AtomIcon extends js.Array[js.Any]

def Atom(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AtomIcon, color, size)
