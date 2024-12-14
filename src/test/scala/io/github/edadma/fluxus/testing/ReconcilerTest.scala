package io.github.edadma.fluxus.testing

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.core.{commit, createDOM, diff, reconcile}
import io.github.edadma.fluxus.core.DOMOperation.*
import org.scalajs.dom

import scala.scalajs.js

class ReconcilerTest extends DOMSpec {
  "TextNode reconciliation" should "generate correct operations and update DOM when text changes" in {
    val container = getContainer

    // Create and mount initial text node
    val oldNode = TextNode("Hello", None, None)
    createDOM(oldNode, container)

    // Verify initial state
    container.textContent shouldBe "Hello"

    // Create new text node with different content
    val newNode = TextNode("World", None, None)

    // Get and verify operations
    val ops = diff(Some(oldNode), Some(newNode))
    ops shouldBe Seq(UpdateText(oldNode, "World"))

    // Commit operations and verify DOM
    ops.foreach(op => commit(op, container))
    container.textContent shouldBe "World"
  }

  it should "generate no operations when text hasn't changed" in {
    val container = getContainer

    // Create and mount initial text node
    val oldNode = TextNode("Hello", None, None)
    createDOM(oldNode, container)

    // Get reference to original DOM node
    val originalDomNode = container.firstChild

    // Create new text node with same content
    val newNode = TextNode("Hello", None, None)

    // Verify no operations generated
    val ops = diff(Some(oldNode), Some(newNode))
    ops shouldBe empty

    // Commit operations and verify DOM unchanged
    ops.foreach(op => commit(op, container))
    container.firstChild shouldBe originalDomNode
    container.textContent shouldBe "Hello"
  }

  it should "generate remove operation for node removal" in {
    val container = getContainer

    // Create and mount initial text node
    val oldNode = TextNode("Hello", None, None)
    createDOM(oldNode, container)

    // Verify initial state
    container.hasChildNodes() shouldBe true

    // Get and verify operations
    val ops = diff(Some(oldNode), None)
    ops shouldBe Seq(RemoveNode(oldNode))

    // Commit operations and verify DOM
    ops.foreach(op => commit(op, container))
    container.hasChildNodes() shouldBe false
  }

  "Element attribute reconciliation" should "generate correct update operations" in {
    val container = getContainer

    val oldNode = ElementNode("div", Map("class" -> "old"), Map(), Vector(), None, None, None)
    val newNode = ElementNode("div", Map("class" -> "new"), Map(), Vector(), None, None, None)

    // Create initial DOM
    createDOM(oldNode, container)

    // Verify initial state
    container.firstChild.asInstanceOf[dom.Element].getAttribute("class") shouldBe "old"

    // Get and verify operations
    val ops = diff(Some(oldNode), Some(newNode))
    ops shouldBe Seq(
      UpdateProps(
        oldNode,
        propsToRemove = Set(),
        propsToAdd = Map("class" -> "new"),
        eventsToRemove = Set(),
        eventsToAdd = Map(),
      ),
    )

    // Commit operations and verify DOM
    ops.foreach(op => commit(op, container))
    container.firstChild.asInstanceOf[dom.Element].getAttribute("class") shouldBe "new"
  }

  "Component reconciliation" should "generate rerender operation when props change" in {
    val container = getContainer

    case class Props(value: String)

    val component = ((props: Props) => TextNode(props.value, None, None)).asInstanceOf[Product => FluxusNode]
    val oldNode   = ComponentNode(component, Props("old"), None)
    val newNode   = ComponentNode(component, Props("new"), None)

    // Create initial DOM
    createDOM(oldNode, container)

    // Verify initial state
    container.textContent shouldBe "old"

    // Get and verify operations
    val ops = diff(Some(oldNode), Some(newNode))
    ops shouldBe Seq(RerenderComponent(oldNode, newNode))

    // Commit operations and verify DOM
    ops.foreach(op => commit(op, container))
    container.textContent shouldBe "new"
  }

  it should "generate no operations when props haven't changed" in {
    val container = getContainer

    case class Props(value: String)

    val component = ((props: Props) => TextNode(props.value, None, None)).asInstanceOf[Product => FluxusNode]
    val oldNode   = ComponentNode(component, Props("same"), None)
    val newNode   = ComponentNode(component, Props("same"), None)

    // Create initial DOM
    createDOM(oldNode, container)

    // Get operations
    val ops = diff(Some(oldNode), Some(newNode))

    // Verify no operations generated
    ops shouldBe empty

    // Verify DOM remains unchanged
    container.textContent shouldBe "same"
  }

  "Event handler reconciliation" should "add new event handlers" in {
    val container = getContainer
    var clicked   = false

    // Create nodes with and without click handler
    val oldNode = button()
    val newNode = button(onClick := (() => clicked = true))

    // Create initial DOM
    createDOM(oldNode, container)
    val buttonElem = container.firstChild.asInstanceOf[dom.Element]

    // Verify no handler initially
    val clickEvent = dom.document.createEvent("Event")

    clickEvent.asInstanceOf[js.Dynamic].initEvent("click", true, true)
    buttonElem.dispatchEvent(clickEvent)
    clicked shouldBe false

    // Get and verify operations
    val ops = diff(Some(oldNode), Some(newNode))

    // Commit changes
    ops.foreach(commit(_, container))

    // Test new handler
    buttonElem.dispatchEvent(clickEvent)
    clicked shouldBe true
  }

  it should "remove old event handlers" in {
    val container = getContainer
    var clicked   = false

    // Create nodes with and without click handler
    val oldNode = button(onClick := (() => clicked = true))
    val newNode = button()

    // Create initial DOM
    createDOM(oldNode, container)
    val buttonElem = container.firstChild.asInstanceOf[dom.Element]

    // Verify initial handler works
    val clickEvent = dom.document.createEvent("Event")

    clickEvent.asInstanceOf[js.Dynamic].initEvent("click", true, true)
    buttonElem.dispatchEvent(clickEvent)
    clicked shouldBe true

    // Reset flag
    clicked = false

    // Get and verify operations
    val ops = diff(Some(oldNode), Some(newNode))

    ops should matchPattern {
      case Seq(UpdateProps(_, _, _, eventsToRemove, _)) if eventsToRemove == Set("onClick") =>
    }

    // Commit changes
    ops.foreach(commit(_, container))

    // Verify handler was removed
    buttonElem.dispatchEvent(clickEvent)
    clicked shouldBe false
  }

  it should "update existing event handlers" in {
    val container = getContainer
    var count1    = 0
    var count2    = 0

    // Create nodes with different click handlers
    val oldNode = button(onClick := (() => count1 += 1))
    val newNode = button(onClick := (() => count2 += 1))

    // Create initial DOM
    createDOM(oldNode, container)
    val buttonElem = container.firstChild.asInstanceOf[dom.Element]

    // Test initial handler
    val clickEvent = dom.document.createEvent("Event")

    clickEvent.asInstanceOf[js.Dynamic].initEvent("click", true, true)
    buttonElem.dispatchEvent(clickEvent)
    count1 shouldBe 1
    count2 shouldBe 0

    // Get and verify operations
    val ops = diff(Some(oldNode), Some(newNode))

    // Commit changes
    ops.foreach(commit(_, container))

    // Test new handler
    buttonElem.dispatchEvent(clickEvent)
    count1 shouldBe 1 // Old handler shouldn't fire
    count2 shouldBe 1 // New handler should fire
  }

  "List reconciliation" should "reuse nodes with matching keys" in withDebugLogging("reuse nodes with matching keys") {
    val container = getContainer

    // Modified to include key in props
    case class ItemProps(key: String, label: String)

    var instanceCounts = Map[String, Int]()

    val Item: ItemProps => FluxusNode = props => {
      instanceCounts = instanceCounts.updated(
        props.key, // Use key instead of id
        instanceCounts.getOrElse(props.key, 0) + 1,
      )

      logger.debug(
        s"Creating instance for item ${props.key}",
        category = "Test",
        opId = 1,
        Map(
          "key"           -> props.key,
          "instanceCount" -> instanceCounts(props.key),
        ),
      )

      div(
        cls := "item",
        // Remove key attribute since it's in props
        s"${props.label} (instance ${instanceCounts(props.key)})",
      )
    }

    // Initial list with keys in props
    val initialItems = Vector(
      ItemProps("1", "First"),
      ItemProps("2", "Second"),
    )

    val oldNode = div(
      cls := "list",
      initialItems.map(props => Item <> props),
    )
    createDOM(oldNode, container)

    logger.debug(
      "After initial render",
      category = "Test",
      opId = 1,
      Map(
        "instanceCounts" -> instanceCounts.toString,
        "domContent"     -> container.innerHTML,
      ),
    )

    // Verify initial render created one instance of each
    instanceCounts("1") shouldBe 1
    instanceCounts("2") shouldBe 1

    // Reorder and reconcile
    val reorderedItems = Vector(
      ItemProps("2", "Second"),
      ItemProps("1", "First"),
    )

    val newNode = div(
      cls := "list",
      reorderedItems.map(props => Item <> props),
    )

    reconcile(Some(oldNode), Some(newNode), container)

    logger.debug(
      "After reconciliation",
      category = "Test",
      opId = 1,
      Map(
        "instanceCounts" -> instanceCounts.toString,
        "domContent"     -> container.innerHTML,
        "expected"       -> "Should reuse instances (count=1) for items with same key",
        "actual"         -> "Current state of instance counts",
      ),
    )

    // Each key should still only have one instance despite reordering
    instanceCounts("1") shouldBe 1
    instanceCounts("2") shouldBe 1

    val items = container.querySelectorAll(".item")
    items(0).textContent shouldBe "Second (instance 1)"
    items(1).textContent shouldBe "First (instance 1)"
  }
}
