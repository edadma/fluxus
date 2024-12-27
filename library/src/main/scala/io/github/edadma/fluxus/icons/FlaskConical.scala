package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/flask-conical", JSImport.Default)
private object FlaskConicalIcon extends js.Array[js.Any]

def FlaskConical(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FlaskConicalIcon, color, size)
