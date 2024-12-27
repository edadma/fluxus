package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/server-crash", JSImport.Default)
private object ServerCrashIcon extends js.Array[js.Any]

def ServerCrash(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ServerCrashIcon, color, size)
