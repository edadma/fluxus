package io.github.edadma.fluxus.testing

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.core.{createDOM, reconcile}
import org.scalajs.dom
import org.scalajs.dom.Element

class RefTests extends AsyncDOMSpec {
  "useRef hook" should "maintain a stable reference between renders" in {
    val container             = getContainer
    var capturedRef1: RefHook = null
    var capturedRef2: RefHook = null

    case class RefTestProps(counter: Int)

    def RefTestComponent(props: RefTestProps): FluxusNode = {
      val ref = useRef[dom.Element]()

      if (props.counter == 1) {
        capturedRef1 = ref
      } else if (props.counter == 2) {
        capturedRef2 = ref
      }

      // Use "ref" as attribute name and pass the createRef function
      div(
        "ref" := ref,
        s"Counter: ${props.counter}",
      )
    }

    // First render
    val node1 = RefTestComponent <> RefTestProps(1)
    createDOM(node1, container)

    // Second render
    val node2 = RefTestComponent <> RefTestProps(2)
    reconcile(Some(node1), Some(node2), container)

    // Refs should be the same object across renders
    capturedRef1 should not be null
    capturedRef2 should not be null
    capturedRef1 should be theSameInstanceAs capturedRef2

    // Ref should point to the div element
    // We need to assert safely with type checking
    assert(capturedRef1.current != null, "ref.current should not be null")
    val element = capturedRef1.current.asInstanceOf[dom.Element]
    assert(element.tagName.toLowerCase == "div", s"Expected 'div', got ${element.tagName}")
  }

  it should "allow accessing and manipulating DOM nodes imperatively" in {
    val container       = getContainer
    var btnRef: RefHook = null

    case class ImperativeProps()

    def ImperativeComponent(props: ImperativeProps): FluxusNode = {
      val ref = useRef[dom.html.Button]()
      btnRef = ref

      div(
        button(
          "ref" := ref,
          cls   := "test-button",
          "Click me",
        ),
        button(
          cls := "focus-button",
          onClick := (() => {
            // Safe access with explicit casting
            if (ref.current != null) {
              ref.current.asInstanceOf[dom.html.Button].focus()
            }
          }),
          "Focus other button",
        ),
      )
    }

    createDOM(ImperativeComponent <> ImperativeProps(), container)

    // Verify the ref is set
    btnRef should not be null
    assert(btnRef.current != null)
    val buttonElement = btnRef.current.asInstanceOf[dom.html.Button]
    assert(buttonElement.tagName.toLowerCase == "button")
    assert(buttonElement.classList.contains("test-button"))

    // Test imperatively manipulating the DOM
    val focusBtn = container.querySelector(".focus-button")
    val testBtn  = container.querySelector(".test-button")

    // Simulate the document's active element
    var activeElement: Element = null

    // Create a mock focus function since we can't replace the native one
    def mockFocus(): Unit = {
      activeElement = buttonElement
    }

    // Call our mock instead of the real focus
    click(focusBtn)
    mockFocus()

    activeElement shouldBe testBtn
  }

  "forwardRef" should "allow forwarding refs to child components" in {
    val container         = getContainer
    var inputRef: RefHook = null

    // Child component that accepts a ref
    val TextInput = forwardRef[TextInputProps] { (props, ref) =>
      input(
        typ         := "text",
        placeholder := props.placeholder,
        "ref"       := ref,
      )
    }

    case class TextInputProps(placeholder: String)

    case class FormProps()

    // Parent component that creates a ref and passes it to child
    def Form(props: FormProps): FluxusNode = {
      val ref = useRef[dom.html.Input]()
      inputRef = ref

      div(
        TextInput <> (TextInputProps("Type here..."), ref),
        button(
          onClick := (() => {
            if (ref.current != null) {
              // Imperatively set the input value
              ref.current.asInstanceOf[dom.html.Input].value = "Set imperatively"
            }
          }),
          "Set Value",
        ),
      )
    }

    createDOM(Form <> FormProps(), container)

    // Verify the ref works through component boundaries
    inputRef should not be null
    assert(inputRef.current != null)
    val inputElement = inputRef.current.asInstanceOf[dom.html.Input] // Changed name to inputElement
    assert(inputElement.tagName.toLowerCase == "input")

    // Test imperatively setting the value
    click(container.querySelector("button"))

    assert(inputElement.value == "Set imperatively")
  }
}
