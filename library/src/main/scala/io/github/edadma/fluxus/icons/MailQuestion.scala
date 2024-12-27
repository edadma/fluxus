package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/mail-question", JSImport.Default)
private object MailQuestionIcon extends js.Array[js.Any]

def MailQuestion(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MailQuestionIcon, color, size)
