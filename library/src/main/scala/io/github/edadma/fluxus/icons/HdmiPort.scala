package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/hdmi-port", JSImport.Default)
private object HdmiPortIcon extends js.Array[js.Any]

def HdmiPort(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HdmiPortIcon, color, size)
