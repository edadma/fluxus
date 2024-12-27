package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/message-circle-question", JSImport.Default)
private object MessageCircleQuestionIcon extends js.Array[js.Any]

def MessageCircleQuestion(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MessageCircleQuestionIcon, color, size)
