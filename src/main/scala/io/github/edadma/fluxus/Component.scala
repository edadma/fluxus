/* Component.scala

This file defines functions and classes related to component creation and management in your simple Scala.js app framework.

Key components:
- `component` function: A helper function to create `ComponentNode` instances.
- `ComponentInstance` case class: Represents an instance of a component, maintaining its state and hooks.

 */

package io.github.edadma.fluxus // Define the package namespace for the Fluxus framework

import scala.collection.mutable.ArrayBuffer // Import mutable ArrayBuffer for storing hooks

// The `component` function is a helper that creates a `ComponentNode` for a given component function and props.
// It handles the creation of the props map and extraction of the optional `key` prop used for identifying components in dynamic lists.

def component(componentFunction: FluxusComponent)(props: (String, Any)*): FluxusNode = {
  val propsMap = makeProps(props*) // Convert the variable arguments (props) into a Props map
  val key = propsMap.get("key").map(_.toString) // Extract the optional "key" prop, converting it to String if present
  ComponentNode( // Create a new ComponentNode with the specified properties
    key = key, // Set the optional key for component identification (may be None)
    componentFunction = componentFunction, // Set the component function to be rendered
    props = propsMap, // Pass the props map to the component
  )
}

// The `ComponentInstance` case class represents an instance of a component with its own state and hooks.
// Each instance maintains its own props and a collection of hooks (e.g., state variables).
// It also keeps track of the current hook index during rendering to manage hooks correctly.

case class ComponentInstance(
    renderFunction: FluxusComponent, // The component function that returns a FluxusNode (VNode)
    var props: Props,                // The props passed to the component (mutable to allow updates when props change)
    var hooks: ArrayBuffer[Any] =
      ArrayBuffer.empty, // Mutable collection of hooks (state variables) used by the component
    var effects: ArrayBuffer[() => Unit] = ArrayBuffer.empty, // Stores effects and dependencies
):
  var hookIndex: Int = 0 // Index to keep track of the current hook during rendering

  // Resets the hook index before rendering
  def resetHooks(): Unit = hookIndex = 0
