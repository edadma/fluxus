package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cookie", JSImport.Default)
private object CookieIcon extends js.Array[js.Any]

def Cookie(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CookieIcon, color, size)
