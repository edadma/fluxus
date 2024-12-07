//package io.github.edadma.fluxus.core.dom
//
//import io.github.edadma.fluxus.api.*
//import io.github.edadma.fluxus.core.hooks.Hooks.{useEffect, useState}
//import io.github.edadma.fluxus.core.types.*
//import io.github.edadma.fluxus.testing.DOMSpec
//import org.scalajs.dom
//import org.scalajs.dom.Element
//
//class EffectRerunTest extends DOMSpec {
//
//  "useEffect" should "re-run effect when a dependency changes" in withDebugLogging {
//    var effectRunCount = 0
//    case class TestProps()
//
//    def TestComponent(props: TestProps): FluxusNode = {
//      val (count, setCount) = useState(0)
//
//      // Effect that depends on count
//      useEffect(
//        () => {
//          effectRunCount += 1
//          () => {} // cleanup
//        },
//        Seq(count),
//      )
//
//      // Simple UI with a button to increment count
//      div(
//        cls := "container",
//        div(
//          cls := "btn-container",
//          button(
//            cls     := "btn",
//            onClick := { (_: dom.Event) => setCount(count + 1) },
//            "Increment",
//          ),
//        ),
//        div(
//          cls := "count-display",
//          span(
//            cls := "ml-2",
//            count.toString,
//          ),
//        ),
//      )
//    }
//
//    // Mount the component
//    val component = Component.create(
//      render = TestComponent(_),
//      props = TestProps(),
//      opId = 1,
//      name = Some("TestComponent"),
//    )
//
//    DOMOperations.mount(component, getContainer)
//
//    // Initial render should run effect once
//    effectRunCount shouldBe 1
//
//    // Find and click the button
//    val btnElement = getContainer.querySelector("button")
//    val clickEvent = dom.document.createEvent("Event")
//    clickEvent.asInstanceOf[scalajs.js.Dynamic].initEvent("click", true, true)
//    btnElement.dispatchEvent(clickEvent)
//
//    // After click, count changed so effect should run again
//    effectRunCount shouldBe 2
//
//    // Verify UI was updated
//    val countElement = getContainer.querySelector("span")
//    countElement.textContent shouldBe "1"
//  }
//}
