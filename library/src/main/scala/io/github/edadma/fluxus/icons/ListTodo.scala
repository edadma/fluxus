package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/list-todo", JSImport.Default)
private object ListTodoIcon extends js.Array[js.Any]

def ListTodo(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ListTodoIcon, color, size)
