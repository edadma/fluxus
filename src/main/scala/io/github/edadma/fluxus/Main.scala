package io.github.edadma.fluxus

@main def run(): Unit =
  render(Counter, "#app")

def Counter(): VNode = {
  println("DEBUG: Rendering Counter component")
  val (count, setCount) = useState(0)

  div(
    h1(TextNode(s"Counter: $count")),
    button(
      "Increment",
      "onClick" -> (() => setCount(count + 1)),
    ),
  )
}
