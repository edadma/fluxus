package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-question", JSImport.Default)
private object FileQuestionIcon extends js.Array[js.Any]

def FileQuestion(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileQuestionIcon, color, size)
