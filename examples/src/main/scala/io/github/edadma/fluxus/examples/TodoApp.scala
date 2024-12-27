package io.github.edadma.fluxus.examples

import io.github.edadma.fluxus.*
import org.scalajs.dom
import org.scalajs.dom.KeyboardEvent

// Models
case class Todo(id: Long, text: String, completed: Boolean)
case class TodoProps()

enum Filter:
  case All, Active, Completed

object TodoApp:
  def App: FluxusNode = TodoApp <> TodoProps()

  def TodoApp(props: TodoProps): FluxusNode = {
    // State hooks
    val (todos, _, updateTodos)      = useState(Vector[Todo]())
    val (newTodo, setNewTodo, _)     = useState("")
    val (filter, setFilter, _)       = useState(Filter.All)
    val (editingId, setEditingId, _) = useState(Option.empty[Long])
    val (editText, setEditText, _)   = useState("")

    def handleAdd() =
      if newTodo.trim.nonEmpty then
        updateTodos(prev =>
          prev :+ Todo(
            id = System.currentTimeMillis(),
            text = newTodo.trim,
            completed = false,
          ),
        )
        setNewTodo("")

    def handleToggle(id: Long) =
      updateTodos(prev =>
        prev.map(todo =>
          if todo.id == id then todo.copy(completed = !todo.completed)
          else todo,
        ),
      )

    def handleDelete(id: Long) =
      updateTodos(prev => prev.filterNot(_.id == id))

    def handleBulkToggle() = {
      val allCompleted = todos.forall(_.completed)
      updateTodos(prev => prev.map(_.copy(completed = !allCompleted)))
    }

    def startEditing(todo: Todo) = {
      setEditingId(Some(todo.id))
      setEditText(todo.text)
    }

    def handleEdit(id: Long, e: KeyboardEvent) = {
      if (e.key == "Enter" && editText.trim.nonEmpty) {
        updateTodos(prev =>
          prev.map(todo =>
            if todo.id == id then todo.copy(text = editText.trim)
            else todo,
          ),
        )
        setEditingId(None)
        setEditText("")
      } else if (e.key == "Escape") {
        setEditingId(None)
        setEditText("")
      }
    }

    def filteredTodos = todos.filter {
      case todo => filter match
          case Filter.All       => true
          case Filter.Active    => !todo.completed
          case Filter.Completed => todo.completed
    }

    div(
      cls := "max-w-2xl mx-auto p-4",
      div(
        cls := "card bg-base-200 shadow-xl",
        div(
          cls := "card-body",
          // Header
          div(
            cls := "todo-header",
            h1(
              cls := "text-3xl font-bold text-center mb-8",
              "Todo App",
            ),
          ),

          // Input form with bulk toggle
          div(
            cls := "todo-input flex gap-2 mb-4",
            if todos.nonEmpty then
              button(
                cls     := "btn btn-circle btn-sm btn-ghost",
                onClick := (() => handleBulkToggle()),
                if todos.forall(_.completed) then "☐" else "☑",
              )
            else
              ""
            ,
            input(
              typ         := "text",
              value       := newTodo,
              onInput     := ((e: dom.Event) => setNewTodo(e.target.asInstanceOf[dom.html.Input].value)),
              placeholder := "What needs to be done?",
              cls         := "input input-bordered flex-grow",
            ),
            button(
              cls     := "btn btn-primary add-todo",
              onClick := (() => handleAdd()),
              "Add",
            ),
          ),

          // Filter tabs
          div(
            cls := "tabs tabs-boxed justify-center mb-4",
            button(
              cls     := s"tab ${if filter == Filter.All then "tab-active" else ""}",
              onClick := (() => setFilter(Filter.All)),
              "All",
            ),
            button(
              cls     := s"tab ${if filter == Filter.Active then "tab-active" else ""}",
              onClick := (() => setFilter(Filter.Active)),
              "Active",
            ),
            button(
              cls     := s"tab ${if filter == Filter.Completed then "tab-active" else ""}",
              onClick := (() => setFilter(Filter.Completed)),
              "Completed",
            ),
          ),

          // Todo list
          div(
            cls := "todo-list space-y-2",
            if filteredTodos.isEmpty then
              div(
                cls := "alert",
                p(
                  cls := "text-center",
                  "No todos found",
                ),
              )
            else
              filteredTodos.map { todo =>
                div(
                  key := todo.id.toString,
                  cls := "todo-item flex items-center gap-2 p-2 bg-base-100 rounded-lg",
                  input(
                    typ      := "checkbox",
                    checked  := todo.completed,
                    onChange := (() => handleToggle(todo.id)),
                    cls      := "checkbox",
                  ),
                  if editingId.contains(todo.id) then
                    input(
                      typ   := "text",
                      value := editText,
                      onChange := ((e: dom.Event) =>
                        setEditText(e.target.asInstanceOf[dom.html.Input].value)
                      ),
                      onKeyDown   := ((e: KeyboardEvent) => handleEdit(todo.id, e)),
                      cls         := "input input-bordered input-sm flex-grow",
                      placeholder := "Press Enter to save, Escape to cancel",
                    )
                  else
                    span(
                      cls        := s"flex-grow ${if todo.completed then "line-through opacity-50" else ""}",
                      onDblClick := (() => startEditing(todo)),
                      todo.text,
                    )
                  ,
                  button(
                    onClick := (() => handleDelete(todo.id)),
                    cls     := "btn btn-ghost btn-sm text-error",
                    "Delete",
                  ),
                )
              },
          ),

          // Footer
          div(
            cls := "todo-footer flex justify-between items-center mt-4",
            span(
              s"${todos.count(!_.completed)} items left",
            ),
            if todos.exists(_.completed) then
              button(
                onClick := (() => updateTodos(_.filterNot(_.completed))),
                cls     := "btn btn-ghost btn-sm",
                "Clear completed",
              )
            else
              "",
          ),
        ),
      ),
    )
  }
