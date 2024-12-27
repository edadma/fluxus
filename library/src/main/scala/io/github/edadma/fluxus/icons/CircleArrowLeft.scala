package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/circle-arrow-left", JSImport.Default)
private object CircleArrowLeftIcon extends js.Array[js.Any]

def CircleArrowLeft(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CircleArrowLeftIcon, color, size)
