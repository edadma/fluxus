package io.github.edadma.fluxus

import io.github.edadma.fluxus.core.ComponentInstance

case class Context[T](defaultValue: T) {
  private var currentValue: T = defaultValue

  case class ProviderProps(value: T, children: FluxusNode)

  def Provider: ProviderProps => FluxusNode = props => {
    logger.debug(
      "Provider component factory called",
      category = "Context",
      Map("value" -> props.value.toString),
    )

    // Create a component that will handle setting/restoring the context value
    def ContextProvider(p: ProviderProps): FluxusNode = {
      logger.debug(
        "ContextProvider rendering",
        category = "Context",
        Map(
          "value"    -> p.value.toString,
          "instance" -> ComponentInstance.current.map(_.id).getOrElse("none"),
        ),
      )

      val prevValue = currentValue
      currentValue = p.value

      try {
        p.children
      } finally {
        currentValue = prevValue
      }
    }

    // Return a component node that will handle the context value
    ContextProvider <> props
  }

  def getValue: T = {
    logger.debug(
      "Getting context value",
      category = "Context",
      Map("value" -> currentValue.toString),
    )
    currentValue
  }
}

def createContext[T](defaultValue: T): Context[T] = Context(defaultValue)

def useContext[T](context: Context[T]): T = {
  ComponentInstance.current.getOrElse(
    throw new Error("useContext must be called within component render"),
  )

  context.getValue
}
