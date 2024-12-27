package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/book-open", JSImport.Default)
private object BookOpenIcon extends js.Array[js.Any]

def BookOpen(color: String = "currentColor", size: Int = 24): RawNode = createIcon(BookOpenIcon, color, size)
