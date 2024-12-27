package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/codesandbox", JSImport.Default)
private object CodesandboxIcon extends js.Array[js.Any]

def Codesandbox(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CodesandboxIcon, color, size)
