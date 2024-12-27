package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/section", JSImport.Default)
private object SectionIcon extends js.Array[js.Any]

def Section(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SectionIcon, color, size)
