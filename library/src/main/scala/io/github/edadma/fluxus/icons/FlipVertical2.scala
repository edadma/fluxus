package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/flip-vertical-2", JSImport.Default)
private object FlipVertical2Icon extends js.Array[js.Any]

def FlipVertical2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FlipVertical2Icon, color, size)
