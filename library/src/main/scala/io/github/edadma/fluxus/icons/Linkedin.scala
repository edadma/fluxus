package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/linkedin", JSImport.Default)
private object LinkedinIcon extends js.Array[js.Any]

def Linkedin(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LinkedinIcon, color, size)
