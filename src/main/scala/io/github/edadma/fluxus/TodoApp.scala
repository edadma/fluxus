package io.github.edadma.fluxus

import scala.language.postfixOps
import Implicits.*

import scala.scalajs.js
import org.scalajs.dom
import org.scalajs.dom.Event

case class Todo(id: String, text: String, completed: Boolean)
case class TodoListProps(items: Seq[Todo], onToggle: String => Unit)
case class TodoItemProps(item: Todo, onToggle: () => Unit)
case class TodoInputProps(onSubmit: String => Unit)

val TodoInput: FC[TodoInputProps] = {
  case TodoInputProps(onSubmit) =>
    val (text, setText) = useState("")

    div(
      cls := "join w-full max-w-md",
      input(
        typ         := "text",
        value       := text,
        cls         := "input input-bordered join-item w-full",
        placeholder := "What needs to be done?",
        "onInput" -> ((e: Event) =>
          setText(e.target.asInstanceOf[dom.HTMLInputElement].value)
        ),
      ),
      button(
        cls := "btn btn-primary join-item",
        "onClick" -> (() => {
          if (text.nonEmpty) {
            onSubmit(text)
            setText("")
          }
        }),
        "Add",
      ),
    )
}

val TodoItem: FC[TodoItemProps] = {
  case TodoItemProps(item, onToggle) =>
    div(
      cls := "flex items-center gap-4 p-4 border-b border-base-300 hover:bg-base-200 transition-colors",
      div(
        cls := "form-control",
        label(
          cls := "cursor-pointer label",
          input(
            typ       := "checkbox",
            cls       := "checkbox checkbox-primary",
            checked   := item.completed,
            "onClick" -> onToggle,
          ),
        ),
      ),
      span(
        cls := (if (item.completed) "line-through opacity-50 flex-grow" else "flex-grow"),
        item.text,
      ),
    )
}

val TodoList: FC[TodoListProps] = {
  case TodoListProps(items, onToggle) =>
    div(
      cls := "rounded-lg border border-base-300 bg-base-100 overflow-hidden",
      if (items.isEmpty)
        div(
          cls := "p-8 text-center text-base-content/50",
          "No todos yet. Add one above!",
        )
      else
        items.map(item =>
          TodoItem <> TodoItemProps(item, () => onToggle(item.id)),
        ),
    )
}

val TodoApp: () => FluxusNode = () => {
  val (todos, setTodos) = useState(Seq[Todo]())

  def addTodo(text: String): Unit =
    setTodos(todos :+ Todo(js.Date.now().toString, text, false))

  def toggleTodo(id: String): Unit =
    setTodos(todos.map(todo =>
      if (todo.id == id) todo.copy(completed = !todo.completed)
      else todo,
    ))

  div(
    cls := "min-h-screen bg-base-200 flex flex-col items-center py-8 px-4",
    div(
      cls := "w-full max-w-2xl space-y-8",
      div(
        cls := "text-center",
        h1(
          cls := "text-4xl font-bold text-base-content",
          "Todo List",
        ),
        p(
          cls := "text-base-content/60 mt-2",
          "Keep track of your tasks",
        ),
      ),
      TodoInput <> TodoInputProps(addTodo),
      TodoList <> TodoListProps(todos, toggleTodo),
    ),
  )
}

@main def run(): Unit = renderApp("app", TodoApp)
