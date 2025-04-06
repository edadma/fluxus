package io.github.edadma.fluxus.examples

import io.github.edadma.fluxus.*

def ToggleApp = {
  val (showFirstComponent, setShowFirstComponent, _) = useState(false)

  logger.debug(
    "Rendering ToggleApp",
    category = "Test",
    Map("showFirstComponent" -> showFirstComponent.toString),
  )

  div(
    button(
      cls     := "btn btn-primary",
      onClick := (() => setShowFirstComponent(!showFirstComponent)),
      if (showFirstComponent) "Show Second Component" else "Show First Component",
    ),
    if (showFirstComponent) {
      FirstComponent <> FirstComponentProps("I am the first component")
    } else {
      SecondComponent <> SecondComponentProps("I am the second component")
    },
  )
}

// First component
case class FirstComponentProps(message: String)

def FirstComponent: FirstComponentProps => FluxusNode = props => {
  logger.debug(
    "Rendering FirstComponent",
    category = "Test",
    Map("message" -> props.message),
  )

  div(cls := "first-component", props.message)
}

// Second component
case class SecondComponentProps(message: String)

def SecondComponent: SecondComponentProps => FluxusNode = props => {
  logger.debug(
    "Rendering SecondComponent",
    category = "Test",
    Map("message" -> props.message),
  )

  div(cls := "second-component", props.message)
}
