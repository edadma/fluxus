package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/creative-commons", JSImport.Default)
private object CreativeCommonsIcon extends js.Array[js.Any]

def CreativeCommons(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CreativeCommonsIcon, color, size)
