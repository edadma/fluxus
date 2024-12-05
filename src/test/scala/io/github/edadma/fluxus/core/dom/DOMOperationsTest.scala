package io.github.edadma.fluxus.core.dom

import io.github.edadma.fluxus.core.types.*
import io.github.edadma.fluxus.core.hooks.Hooks
import io.github.edadma.fluxus.core.hooks.Hooks.useState
import io.github.edadma.fluxus.logging.Logger
import io.github.edadma.fluxus.logging.Logger.Category
import io.github.edadma.fluxus.testing.DOMSpec
import org.scalajs.dom
import org.scalajs.dom.html

import scala.scalajs.js

class DOMOperationsTest extends DOMSpec {
  case class TestProps()

  it should "update DOM when state changes in a nested component" in {
    var renders = 0

    def TestComponent(props: TestProps): FluxusNode = {
      renders += 1
      val renderCount = renders // Capture current value
      val opId        = Logger.nextOperationId

      Logger.debug(
        Category.Component,
        "Component rendering",
        opId,
        Map("renderCount" -> renderCount),
      )

      val (count, setCount) = useState(0)

      def handleClick(e: dom.Event): Unit = {
        // Log after node is created but before state update
        Logger.debug(
          Category.StateEffect,
          "Button clicked - updating state",
          opId,
          Map("currentCount" -> count),
        )
        setCount(count + 1)
      }

      // Create the node without any self-references
      val button = ElementNode(
        tag = "button",
        props = Map.empty,
        events = Map("onClick" -> handleClick),
        children = Vector.empty,
        parent = None,
        domNode = None,
        key = None,
      )

      val span = ElementNode(
        tag = "span",
        props = Map.empty,
        events = Map.empty,
        children = Vector(TextNode(count.toString, None, None, None)),
        parent = None,
        domNode = None,
        key = None,
      )

      val node = ElementNode(
        tag = "div",
        props = Map.empty,
        events = Map.empty,
        children = Vector(button, span),
        parent = None,
        domNode = None,
        key = None,
      )

      // Log DOM node information after creation
      Logger.debug(
        Category.VirtualDOM,
        "Element node state",
        opId,
        Map(
          "hasDomNode"  -> node.domNode.isDefined,
          "domNodeType" -> node.domNode.map(_.nodeName).getOrElse("none"),
        ),
      )

      Logger.debug(
        Category.Component,
        "Render complete",
        opId,
        Map(
          "renderCount" -> renderCount,
          "stateValue"  -> count,
        ),
      )

      node
    }

    val component = Component.create(
      render = TestComponent,
      props = TestProps(),
      opId = 1,
      name = Some("TestComponent"),
    )

    val container = getContainer
    DOMOperations.mount(component, container)

    // Verify initial state
    val span = container.querySelector("span")
    span.textContent shouldBe "0"

    // Log DOM state before click
    val button = container.querySelector("button")
    Logger.debug(
      Category.VirtualDOM,
      "DOM state before click",
      Logger.nextOperationId,
      Map(
        "spanContent"        -> span.textContent,
        "buttonParentExists" -> (button.parentNode != null),
        "buttonParentType"   -> button.parentNode.nodeName,
        "spanParentExists"   -> (span.parentNode != null),
        "spanParentType"     -> span.parentNode.nodeName,
      ),
    )

    // Simulate click
    val clickEvent = dom.document.createEvent("Event")
    clickEvent.asInstanceOf[js.Dynamic].initEvent("click", true, true)

    Logger.debug(
      Category.VirtualDOM,
      "Dispatching click event",
      Logger.nextOperationId,
      Map("currentContent" -> span.textContent),
    )

    button.dispatchEvent(clickEvent)

    // Log DOM state after click
    Logger.debug(
      Category.VirtualDOM,
      "DOM state after click",
      Logger.nextOperationId,
      Map(
        "spanContent"        -> span.textContent,
        "buttonParentExists" -> (button.parentNode != null),
        "buttonParentType"   -> button.parentNode.nodeName,
        "spanParentExists"   -> (span.parentNode != null),
        "spanParentType"     -> span.parentNode.nodeName,
      ),
    )

    // Verify update
    span.textContent shouldBe "1"
    renders shouldBe 2
  }

  "Component with state" should "render initial state correctly" in {
    // Test just initial render
    def RenderTest(props: TestProps): FluxusNode = {
      val (count, _) = useState(0)
      ElementNode(
        tag = "div",
        props = Map.empty,
        events = Map.empty,
        children = Vector(TextNode(count.toString, None, None, None)),
        parent = None,
        domNode = None,
        key = None,
      )
    }

    val component = Component.create(
      render = RenderTest,
      props = TestProps(),
      opId = 1,
      name = Some("RenderTest"),
    )

    DOMOperations.mount(component, getContainer)
    getContainer.textContent shouldBe "0"
  }

//  it should "respond to click events" in withDebugLogging {
//    var clicked = false
//
//    def ClickTest(props: TestProps): FluxusNode = {
//      ElementNode(
//        tag = "button",
//        props = Map.empty,
//        events = Map("onClick" -> ((_: dom.Event) => clicked = true)),
//        children = Vector.empty,
//        parent = None,
//        domNode = None,
//        key = None,
//      )
//    }
//
//    val component = Component.create(
//      render = ClickTest,
//      props = TestProps(),
//      opId = 1,
//      name = Some("ClickTest"),
//    )
//
//    DOMOperations.mount(component, getContainer)
//
//    val button     = getContainer.querySelector("button")
//    val clickEvent = dom.document.createEvent("Event")
//    clickEvent.asInstanceOf[js.Dynamic].initEvent("click", true, true)
//    button.dispatchEvent(clickEvent)
//
//    clicked shouldBe true
//  }

//  it should "update state correctly" in withDebugLogging {
//    var stateValue = 0
//
//    def StateTest(props: TestProps): FluxusNode = {
//      val (count, setCount) = useState(0)
//      stateValue = count
//      ElementNode(
//        tag = "button",
//        props = Map.empty,
//        events = Map("onClick" -> ((_: dom.Event) => setCount(count + 1))),
//        children = Vector.empty,
//        parent = None,
//        domNode = None,
//        key = None,
//      )
//    }
//
//    val component = Component.create(
//      render = StateTest,
//      props = TestProps(),
//      opId = 1,
//      name = Some("StateTest"),
//    )
//
//    DOMOperations.mount(component, getContainer)
//    stateValue shouldBe 0
//
//    val button     = getContainer.querySelector("button")
//    val clickEvent = dom.document.createEvent("Event")
//    clickEvent.asInstanceOf[js.Dynamic].initEvent("click", true, true)
//    button.dispatchEvent(clickEvent)
//
//    stateValue shouldBe 1
//  }

//  it should "update DOM when state changes in a nested component" in {
//    var renders = 0
//
//    def TestComponent(props: TestProps): FluxusNode = {
//      renders += 1
//      val renderCount = renders // Capture current value
//      val opId        = Logger.nextOperationId
//
//      Logger.debug(
//        Category.Component,
//        "Component rendering",
//        opId,
//        Map("renderCount" -> renderCount),
//      )
//
//      val (count, setCount) = useState(0)
//
//      val node = ElementNode(
//        tag = "div",
//        props = Map.empty,
//        events = Map.empty,
//        children = Vector(
//          ElementNode(
//            tag = "button",
//            props = Map.empty,
//            events = Map("onClick" -> ((_: dom.Event) => {
//              Logger.debug(
//                Category.StateEffect,
//                "Button clicked - updating state",
//                opId,
//                Map("currentCount" -> count),
//              )
//              setCount(count + 1)
//            })),
//            children = Vector.empty,
//            parent = None,
//            domNode = None,
//            key = None,
//          ),
//          ElementNode(
//            tag = "span",
//            props = Map.empty,
//            events = Map.empty,
//            children = Vector(TextNode(count.toString, None, None, None)),
//            parent = None,
//            domNode = None,
//            key = None,
//          ),
//        ),
//        parent = None,
//        domNode = None,
//        key = None,
//      )
//
//      Logger.debug(
//        Category.Component,
//        "Render complete",
//        opId,
//        Map(
//          "renderCount" -> renderCount,
//          "stateValue"  -> count,
//        ),
//      )
//
//      node
//    }
//
//    val component = Component.create(
//      render = TestComponent,
//      props = TestProps(),
//      opId = 1,
//      name = Some("TestComponent"),
//    )
//
//    DOMOperations.mount(component, getContainer)
//    val span = getContainer.querySelector("span")
//    span.textContent shouldBe "0"
//
//    // Simulate click with debug logging
//    val button     = getContainer.querySelector("button")
//    val clickEvent = dom.document.createEvent("Event")
//    clickEvent.asInstanceOf[js.Dynamic].initEvent("click", true, true)
//
//    Logger.debug(
//      Category.VirtualDOM,
//      "Dispatching click event",
//      Logger.nextOperationId,
//      Map("currentContent" -> span.textContent),
//    )
//
//    button.dispatchEvent(clickEvent)
//
//    Logger.debug(
//      Category.VirtualDOM,
//      "After click event",
//      Logger.nextOperationId,
//      Map("newContent" -> span.textContent),
//    )
//
//    span.textContent shouldBe "1"
//    renders shouldBe 2 // Should have initial render and update render
//  }

//  "DOMOperations" should "create a basic element" in {
//    val node = ElementNode(
//      tag = "div",
//      props = Map("class" -> "test"),
//      events = Map.empty,
//      children = Vector.empty,
//      parent = None,
//      domNode = None,
//      key = None,
//    )
//
//    DOMOperations.mount(node, getContainer)
//
//    val div = getContainer.querySelector("div").asInstanceOf[html.Div]
//    div should not be null
//    div.className shouldBe "test"
//  }
//
//  it should "handle nested elements" in {
//    val node = ElementNode(
//      tag = "div",
//      props = Map.empty,
//      events = Map.empty,
//      children = Vector(
//        ElementNode(
//          tag = "span",
//          props = Map("class" -> "child"),
//          events = Map.empty,
//          children = Vector(TextNode("test content", None, None, None)),
//          parent = None,
//          domNode = None,
//          key = None,
//        ),
//      ),
//      parent = None,
//      domNode = None,
//      key = None,
//    )
//
//    DOMOperations.mount(node, getContainer)
//
//    val span = getContainer.querySelector("span").asInstanceOf[html.Span]
//
//    span should not be null
//    span.className shouldBe "child"
//    span.textContent shouldBe "test content"
//  }
//
//  it should "handle text node updates via diffing" in {
//    val oldNode = TextNode("old text", None, None, None)
//    val newNode = TextNode("new text", None, None, None)
//
//    DOMOperations.mount(oldNode, getContainer)
//    getContainer.textContent shouldBe "old text"
//
//    Reconciler.diff(Some(oldNode), Some(newNode), getContainer)
//    getContainer.textContent shouldBe "new text"
//  }
//
//  it should "handle click events" in {
//    var clicked = false
//    val handler = (_: dom.Event) => clicked = true
//
//    val node = ElementNode(
//      tag = "button",
//      props = Map.empty,
//      events = Map("onClick" -> handler),
//      children = Vector(TextNode("Click me", None, None, None)),
//      parent = None,
//      domNode = None,
//      key = None,
//    )
//
//    DOMOperations.mount(node, getContainer)
//
//    val button = getContainer.querySelector("button")
//    button should not be null
//
//    // Simulate click
//    val clickEvent = dom.document.createEvent("Event")
//    clickEvent.asInstanceOf[js.Dynamic].initEvent("click", true, true)
//    button.dispatchEvent(clickEvent)
//    clicked shouldBe true
//  }
//
//  it should "pass event object to handlers" in {
//    var receivedEvent: dom.Event = null
//    val handler                  = (e: dom.Event) => receivedEvent = e
//
//    val node = ElementNode(
//      tag = "button",
//      props = Map.empty,
//      events = Map("onClick" -> handler),
//      children = Vector.empty,
//      parent = None,
//      domNode = None,
//      key = None,
//    )
//
//    DOMOperations.mount(node, getContainer)
//
//    val button = getContainer.querySelector("button")
//    button should not be null
//
//    val clickEvent = dom.document.createEvent("Event")
//    clickEvent.asInstanceOf[js.Dynamic].initEvent("click", true, true)
//    button.dispatchEvent(clickEvent)
//
//    receivedEvent should not be null
//    receivedEvent.`type` shouldBe "click"
//  }
//
//  it should "update DOM after state changes from event" in {
//    val initialText = TextNode("Initial", None, None, None)
//
//    val node = ElementNode(
//      tag = "div",
//      props = Map.empty,
//      events = Map.empty,
//      children = Vector(
//        ElementNode(
//          tag = "button",
//          props = Map.empty,
//          events = Map("onClick" -> ((_: dom.Event) => {
//            // Simulate state update by diffing in new content
//            val newContent = TextNode("Updated", None, None, None)
//            val container  = dom.document.querySelector("span")
//            Reconciler.diff(Some(initialText), Some(newContent), container.asInstanceOf[dom.Element])
//          })),
//          children = Vector.empty,
//          parent = None,
//          domNode = None,
//          key = None,
//        ),
//        ElementNode(
//          tag = "span",
//          props = Map.empty,
//          events = Map.empty,
//          children = Vector(initialText),
//          parent = None,
//          domNode = None,
//          key = None,
//        ),
//      ),
//      parent = None,
//      domNode = None,
//      key = None,
//    )
//
//    DOMOperations.mount(node, getContainer)
//
//    // Verify initial state
//    val span = getContainer.querySelector("span")
//    span.textContent shouldBe "Initial"
//
//    // Simulate click
//    val button     = getContainer.querySelector("button")
//    val clickEvent = dom.document.createEvent("Event")
//    clickEvent.asInstanceOf[js.Dynamic].initEvent("click", true, true)
//    button.dispatchEvent(clickEvent)
//
//    // Verify update
//    span.textContent shouldBe "Updated"
//  }
//
//  it should "trigger diffing after state updates" in {
//    var renderCount  = 0
//    var currentValue = 0
//
//    def renderFn(props: Any): FluxusNode = {
//      renderCount += 1
//      TextNode(currentValue.toString, None, None, None)
//    }
//
//    val instance = ComponentInstance(
//      id = "test",
//      component = renderFn,
//      componentType = "TestComponent",
//      props = (),
//      domNode = Some(getContainer),
//    )
//
//    // Initial render and mount
//    DOMOperations.mount(instance.render(1).get, getContainer)
//
//    // Verify initial render
//    renderCount shouldBe 1
//    getContainer.textContent shouldBe "0"
//
//    // Update value and re-render
//    currentValue = 1
//    DOMOperations.mount(instance.render(2).get, getContainer)
//
//    // Verify update
//    renderCount shouldBe 2
//    getContainer.textContent shouldBe "1"
//  }
//
//  it should "properly maintain hook state between renders" in {
//    var instance: ComponentInstance        = null
//    var hook: Option[(Int, (Int) => Unit)] = None
//
//    def renderFn(props: Any): FluxusNode = {
//      Hooks.setCurrentInstance(instance)
//      val state = useState(0)
//      hook = Some(state) // Store hook reference for testing
//      Hooks.clearCurrentInstance()
//
//      TextNode(state._1.toString, None, None, None)
//    }
//
//    // Create and mount component
//    instance = ComponentInstance(
//      id = "test",
//      component = renderFn,
//      componentType = "TestComponent",
//      props = (),
//      domNode = Some(getContainer),
//    )
//
//    // Initial render
//    val initialRender = instance.render(1)
//    DOMOperations.mount(initialRender.get, getContainer)
//
//    // Verify initial state
//    hook should not be None
//    hook.get._1 shouldBe 0
//    getContainer.textContent shouldBe "0"
//
//    // Update state directly through stored hook reference
//    hook.get._2(1)
//
//    // Verify new state
//    getContainer.textContent shouldBe "1"
//  }

//  it should "handle state updates in components with diffing" in withDebugLogging {
//    case class TestProps()
//
//    var rendered: Option[FluxusNode] = None // Store rendered output
//
//    def TestComponent(props: TestProps): FluxusNode = {
//      val (count, setCount) = useState(0)
//
//      val output = ElementNode(
//        tag = "div",
//        props = Map.empty,
//        events = Map.empty,
//        children = Vector(
//          ElementNode(
//            tag = "button",
//            props = Map.empty,
//            events = Map("onClick" -> ((_: dom.Event) => setCount(count + 1))),
//            children = Vector.empty,
//            parent = None,
//            domNode = None,
//            key = None,
//          ),
//          ElementNode(
//            tag = "span",
//            props = Map.empty,
//            events = Map.empty,
//            children = Vector(TextNode(count.toString, None, None, None)),
//            parent = None,
//            domNode = None,
//            key = None,
//          ),
//        ),
//        parent = None,
//        domNode = None,
//        key = None,
//      )
//
//      rendered = Some(output) // Store for comparison
//      output
//    }
//
//    val component = Component.create(
//      render = TestComponent(_),
//      props = TestProps(),
//      opId = 1,
//      name = Some("TestComponent"),
//    )
//
//    DOMOperations.mount(component, getContainer)
//
//    val span = getContainer.querySelector("span")
//    span.textContent shouldBe "0"
//
//    // Store initial render output
//    val initialRender = rendered
//
//    val button     = getContainer.querySelector("button")
//    val clickEvent = dom.document.createEvent("Event")
//    clickEvent.asInstanceOf[js.Dynamic].initEvent("click", true, true)
//    button.dispatchEvent(clickEvent)
//
//    // Verify we got a different render output
//    rendered should not be initialRender
//
//    span.textContent shouldBe "1"
//  }

//  it should "update element attributes" in withDebugLogging {
//    val oldNode = ElementNode(
//      tag = "div",
//      props = Map("class" -> "old"),
//      events = Map.empty,
//      children = Vector.empty,
//      parent = None,
//      domNode = None,
//      key = None,
//    )
//
//    val newNode = ElementNode(
//      tag = "div",
//      props = Map("class" -> "new"),
//      events = Map.empty,
//      children = Vector.empty,
//      parent = None,
//      domNode = None,
//      key = None,
//    )
//
//    val mountedNode = DOMOperations.mount(oldNode, getContainer)
//    Logger.debug(
//      Category.VirtualDOM,
//      "Node mounted",
//      Logger.nextOperationId,
//      Map(
//        "hasDomNode" -> mountedNode.domNode.isDefined,
//        "props"      -> mountedNode.asInstanceOf[ElementNode].props,
//      ),
//    )
//
//    Reconciler.diff(Some(mountedNode), Some(newNode), getContainer)
//
//    val div = getContainer.querySelector("div").asInstanceOf[html.Element]
//    div.className shouldBe "new"
//  }

//  it should "handle text node updates" in {
//    val oldNode = TextNode("old text", None, None, None)
//    val newNode = TextNode("new text", None, None, None)
//
//    DOMOperations.mount(oldNode, getContainer)
//    Reconciler.diff(Some(oldNode), Some(newNode), getContainer)
//
//    getContainer.textContent shouldBe "new text"
//  }

//  it should "remove nodes" in {
//    val node = ElementNode(
//      tag = "div",
//      props = Map.empty,
//      events = Map.empty,
//      children = Vector.empty,
//      parent = None,
//      domNode = None,
//      key = None,
//    )
//
//    DOMOperations.mount(node, getContainer)
//    Reconciler.diff(Some(node), None, getContainer)
//
//    getContainer.childNodes.length shouldBe 0
//  }
}
