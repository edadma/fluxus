package io.github.edadma.fluxus

import scala.collection.mutable.ArrayBuffer

def component(componentFunction: Props => FluxusNode)(props: (String, Any)*): FluxusNode = {
  val propsMap = makeProps(props*)
  val key      = propsMap.get("key").map(_.toString)
  ComponentNode(
    key = key,
    componentFunction = componentFunction,
    props = propsMap,
  )
}

// Represents an instance of a component with its own hooks (state)
case class ComponentInstance(
                              renderFunction: Props => FluxusNode, // The component function that returns a VNode
                              var props: Props, // The props passed to the component
                              var hooks: ArrayBuffer[Any] = ArrayBuffer.empty, // The hooks (state) used by the component
) {
  // Index to keep track of the current hook during rendering
  var hookIndex: Int = 0

  // Resets the hook index before rendering
  def resetHooks(): Unit = {
    hookIndex = 0
  }
}
