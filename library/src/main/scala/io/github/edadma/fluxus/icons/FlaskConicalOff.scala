package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/flask-conical-off", JSImport.Default)
private object FlaskConicalOffIcon extends js.Array[js.Any]

def FlaskConicalOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FlaskConicalOffIcon, color, size)
