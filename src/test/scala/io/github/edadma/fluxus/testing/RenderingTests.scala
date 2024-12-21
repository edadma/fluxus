//package io.github.edadma.fluxus.testing
//
//import io.github.edadma.fluxus.*
//import io.github.edadma.fluxus.core.{ComponentInstance, createDOM, reconcile}
//
//import scala.concurrent.Future
//
//class RenderingTests extends AsyncDOMSpec {
//  "Optional content" should "appear in correct order regardless of empty div" in withDebugLogging(
//    "appear in correct order regardless of empty div",
//  ) {
//    val container = getContainer
//
//    val TestComponent = () => {
//      val (showText, setShowText) = useState(false)
//
//      div(
//        button(
//          onClick := (() => setShowText(!_)),
//          "Toggle",
//        ),
//        Option.when(showText)(
//          div("toggled"),
//        ),
//        div("last"),
//      )
//    }
//
//    createDOM(TestComponent <> (), container)
//
//    val buttonElem = container.querySelector("button")
//
//    eventually {
//      container.innerHTML shouldBe "<div><button>Toggle</button><div>last</div></div>"
//    }.map { _ =>
//      click(buttonElem)
//    }.flatMap { _ =>
//      eventually {
//        container.innerHTML shouldBe "<div><button>Toggle</button><div>toggled</div><div>last</div></div>"
//      }
//    }
//  }
//}
