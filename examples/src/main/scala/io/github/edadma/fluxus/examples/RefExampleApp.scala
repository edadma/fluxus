package io.github.edadma.fluxus.examples

import io.github.edadma.fluxus.*
import org.scalajs.dom

object RefExampleApp {
  def App: FluxusNode = {
    div(
      cls := "min-h-screen bg-base-200 p-4",
      FocusableForm <> FocusableFormProps("Enter your details"),
      AutofocusExample <> AutofocusExampleProps(),
      MeasureExample <> MeasureExampleProps(),
    )
  }

  // Example 1: Form with focusable inputs
  case class FocusableFormProps(title: String)

  def FocusableForm(props: FocusableFormProps): FluxusNode = {
    logger.debug(
      "About to call first useRef in FocusableForm",
      category = "RefExample",
      Map("props" -> props.toString),
    )
    val nameInputRef = useRef[dom.html.Input]()
    logger.debug(
      "About to call second useRef in FocusableForm",
      category = "RefExample",
      Map("nameInputRef" -> nameInputRef.toString),
    )
    val emailInputRef = useRef[dom.html.Input]()
    logger.debug(
      "About to call third useRef in FocusableForm",
      category = "RefExample",
      Map("emailInputRef" -> emailInputRef.toString),
    )
    val messageInputRef = useRef[dom.html.TextArea]()

    div(
      cls := "card bg-base-100 shadow-xl mb-8",
      div(
        cls := "card-body",
        h2(cls := "card-title", props.title),
        div(
          cls := "form-control w-full mb-4",
          label(cls := "label", span(cls := "label-text", "Name")),
          input(
            typ         := "text",
            cls         := "input input-bordered w-full",
            placeholder := "Your name",
            ref         := createRef(nameInputRef),
          ),
        ),
        div(
          cls := "form-control w-full mb-4",
          label(cls := "label", span(cls := "label-text", "Email")),
          input(
            typ         := "email",
            cls         := "input input-bordered w-full",
            placeholder := "Your email",
            ref         := createRef(emailInputRef),
          ),
        ),
        div(
          cls := "form-control w-full mb-4",
          label(cls := "label", span(cls := "label-text", "Message")),
          textarea(
            cls         := "textarea textarea-bordered w-full h-24",
            placeholder := "Your message",
            ref         := createRef(messageInputRef),
          ),
        ),
        div(
          cls := "flex gap-2 justify-center",
          button(
            cls     := "btn btn-outline",
            onClick := (() => nameInputRef.current.focus()),
            "Focus Name",
          ),
          button(
            cls     := "btn btn-outline",
            onClick := (() => emailInputRef.current.focus()),
            "Focus Email",
          ),
          button(
            cls     := "btn btn-outline",
            onClick := (() => messageInputRef.current.focus()),
            "Focus Message",
          ),
          button(
            cls := "btn btn-primary",
            onClick := (() => {
              if (
                nameInputRef.current.value.nonEmpty &&
                emailInputRef.current.value.nonEmpty &&
                messageInputRef.current.value.nonEmpty
              ) {
                dom.window.alert("Form submitted!")
              } else {
                dom.window.alert("Please fill all fields")
              }
            }),
            "Submit",
          ),
        ),
      ),
    )
  }

  // Example 2: Autofocus component with forwarded ref
  case class AutofocusExampleProps()

  // Reusable input component with ref forwarding
  case class CustomInputProps(
      label: String,
      placeholder: String = "",
      typ: String = "text",
  )

  val CustomInput = forwardRef[CustomInputProps] { (props, ref) =>
    div(
      cls := "form-control w-full mb-4",
      label(cls := "label", span(cls := "label-text", props.label)),
      input(
        typ         := props.typ,
        cls         := "input input-bordered w-full",
        placeholder := props.placeholder,
        "ref"       := createRef(ref.asInstanceOf[RefHook & { type RefType <: dom.html.Input }]),
      ),
    )
  }

  def AutofocusExample(props: AutofocusExampleProps): FluxusNode = {
    val inputRef = useRef[dom.html.Input]()

    // Focus the input on mount
    useEffect(
      () => {
        if (inputRef.current != null) {
          inputRef.current.focus()
        }
        ()
      },
      Seq(),
    )

    div(
      cls := "card bg-base-100 shadow-xl mb-8",
      div(
        cls := "card-body",
        h2(cls := "card-title", "Autofocus with forwarded ref"),
        p(cls  := "mb-4", "This input is automatically focused when the component mounts."),
        CustomInput <> (CustomInputProps("Autofocus input", "This gets auto-focused"), inputRef),
      ),
    )
  }

  // Example 3: Measuring DOM elements
  case class MeasureExampleProps()

  def MeasureExample(props: MeasureExampleProps): FluxusNode = {
    val boxRef                               = useRef[dom.html.Div]()
    val (size, setSize, _)                   = useState("Measure me")
    val (showBig, setShowBig, updateShowBig) = useState(false)

    // Measure the element when size changes
    useEffect(
      () => {
        if (boxRef.current != null) {
          val width  = boxRef.current.clientWidth
          val height = boxRef.current.clientHeight
          setSize(s"Width: ${width}px, Height: ${height}px")
        }
        ()
      },
      Seq(showBig),
    )

    div(
      cls := "card bg-base-100 shadow-xl",
      div(
        cls := "card-body",
        h2(cls := "card-title", "Measure DOM elements"),
        p(cls  := "mb-4", "This example shows how to measure DOM elements using refs."),
        div(
          ref := createRef(boxRef),
          cls := s"bg-primary text-primary-content p-4 rounded-lg transition-all ${
              if showBig then "w-64 h-32" else "w-32 h-16"
            }",
          "Resizable Box",
        ),
        div(cls := "mt-2 text-sm", size),
        button(
          cls     := "btn btn-outline mt-4",
          onClick := (() => updateShowBig(!_)),
          if (showBig) "Make smaller" else "Make bigger",
        ),
      ),
    )
  }
}
