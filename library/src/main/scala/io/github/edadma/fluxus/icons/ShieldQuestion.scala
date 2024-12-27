package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/shield-question", JSImport.Default)
private object ShieldQuestionIcon extends js.Array[js.Any]

def ShieldQuestion(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ShieldQuestionIcon, color, size)
