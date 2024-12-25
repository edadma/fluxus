package io.github.edadma.fluxus.examples

import io.github.edadma.fluxus.*

object ButtonApp {
  def App: FluxusNode =
    val (message, setMessage, _) = useState("Welcome to the React app!")

    div(
      cls := "min-h-screen bg-base-200 flex items-center justify-center",
      div(
        cls := "p-6 bg-base-100 rounded-lg shadow-lg text-center space-y-6",
        h1(cls := "text-2xl font-bold", message),
        div(
          cls := "space-y-2",
          button(
            cls := "btn btn-primary w-full",
            "Say Hello",
            onClick := (() => setMessage("Hello, world!")),
          ),
          button(
            cls := "btn btn-secondary w-full",
            "Say Goodbye",
            onClick := (() => setMessage("Goodbye, world!")),
          ),
          button(
            cls := "btn btn-accent w-full",
            "Welcome to DaisyUI!",
            onClick := (() => setMessage("Welcome to DaisyUI!")),
          ),
        ),
      ),
    )
}
