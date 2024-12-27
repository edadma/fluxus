package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-slash-2", JSImport.Default)
private object CircleSlash2Icon extends js.Array[js.Any]

def CircleSlash2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleSlash2Icon, color, size)
