package io.github.edadma.fluxus

import scala.language.postfixOps
import Implicits.*
import org.scalajs.dom
import org.scalajs.dom.{Event, HTMLInputElement, KeyboardEvent}

case class Todo(id: Int, text: String, completed: Boolean)

//@main def run(): Unit = renderApp("app", TodoApp)

def TodoApp(appProps: Props): FluxusNode =
  RenderTracker.trackRender("TodoApp"):
    val (todos, setTodos)             = useState(List[Todo]())
    val (newTodoText, setNewTodoText) = useState("")
    val (filter, setFilter)           = useState("all") // "all", "active", "completed"

    val activeTodos = todos.count(!_.completed)
    val filteredTodos = filter match
      case "active"    => todos.filter(!_.completed)
      case "completed" => todos.filter(_.completed)
      case _           => todos

    def addTodo(): Unit =
      if newTodoText.trim.nonEmpty then
        setTodos(todos :+ Todo(
          id = todos.map(_.id).maxOption.getOrElse(-1) + 1,
          text = newTodoText.trim,
          completed = false,
        ))
        setNewTodoText("")

    def toggleTodo(id: Int): Unit =
      setTodos(todos.map(todo =>
        if todo.id == id then todo.copy(completed = !todo.completed)
        else todo,
      ))

    def removeTodo(id: Int): Unit =
      setTodos(todos.filterNot(_.id == id))

    def clearCompleted(): Unit =
      setTodos(todos.filterNot(_.completed))

    def handleInput(e: dom.Event): Unit =
      setNewTodoText(e.target.asInstanceOf[HTMLInputElement].value)

    div(
      cls := "min-h-screen bg-base-200 flex items-center justify-center p-4",
      div(
        cls := "card w-96 bg-base-100 shadow-xl",
        div(
          cls := "card-body",
          // Header
          h2(cls := "card-title mb-4", "Todo App"),

          // Input form
          div(
            cls := "form-control",
            div(
              cls := "input-group",
              input(
                typ         := "text",
                placeholder := "What needs to be done?",
                cls         := "input input-bordered w-full",
                value       := newTodoText,
                onInput     := handleInput,
                onKeyUp     := ((e: KeyboardEvent) => if e.key == "Enter" then addTodo()),
              ),
              button(
                cls     := "btn btn-primary",
                onClick := ((_: Event) => addTodo()),
                "Add",
              ),
            ),
          ),

          // Filters
          div(
            cls := "tabs tabs-boxed my-4 justify-center",
            button(
              cls     := s"tab ${if filter == "all" then "tab-active" else ""}",
              onClick := ((_: Event) => setFilter("all")),
              s"All (${todos.length})",
            ),
            button(
              cls     := s"tab ${if filter == "active" then "tab-active" else ""}",
              onClick := ((_: Event) => setFilter("active")),
              s"Active ($activeTodos)",
            ),
            button(
              cls     := s"tab ${if filter == "completed" then "tab-active" else ""}",
              onClick := ((_: Event) => setFilter("completed")),
              s"Completed (${todos.length - activeTodos})",
            ),
          ),

          // Todo list
          ul(
            (cls := "space-y-2") +: filteredTodos.map(todo =>
              li(
                cls := "flex items-center gap-2 p-2 border border-base-300 rounded-lg",
                input(
                  typ      := "checkbox",
                  cls      := "checkbox",
                  checked  := todo.completed,
                  onChange := (() => toggleTodo(todo.id)),
                ),
                span(
                  cls := s"flex-1 ${if todo.completed then "line-through opacity-50" else ""}",
                  todo.text,
                ),
                button(
                  cls     := "btn btn-ghost btn-sm btn-circle",
                  onClick := (() => removeTodo(todo.id)),
                  "×",
                ),
              ),
            )*,
          ),

          // Footer
          div(
            cls := "mt-4 flex justify-between items-center text-sm",
            span(
              if activeTodos == 0 then "No tasks remaining"
              else if activeTodos == 1 then "1 task remaining"
              else s"$activeTodos tasks remaining",
            ),
            if todos.exists(_.completed) then
              button(
                cls     := "btn btn-ghost btn-xs",
                onClick := ((_: Event) => clearCompleted()),
                "Clear completed",
              )
            else
              null,
          ),
        ),
      ),
    )
