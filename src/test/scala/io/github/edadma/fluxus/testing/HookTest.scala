package io.github.edadma.fluxus.testing

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.core.createDOM

class HookTest extends AnyDOMSpec {
  "useState hook" should "maintain state between renders" in {
    val container = getContainer
    case class TestProps()

    def TestComponent(props: TestProps): FluxusNode = {
      val (count, setCount) = useState(0)
      div(count.toString)
    }

    createDOM(TestComponent <> TestProps(), container)
    container.textContent shouldBe "0"
  }

  it should "maintain the same setter function between renders" in {
    val container                      = getContainer
    var setterRef: Option[Int => Unit] = None

    case class TestProps()
    def TestComponent(props: TestProps): FluxusNode = {
      val (_, setter) = useState(0)
      if (setterRef.isEmpty) setterRef = Some(setter)
      else setterRef.get shouldBe setter
      div()
    }

    createDOM(TestComponent <> TestProps(), container)
  }
}
