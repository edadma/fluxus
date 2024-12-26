//package io.github.edadma.fluxus.testing
//
//import io.github.edadma.fluxus.*
//import io.github.edadma.fluxus.core.ComponentInstance
//
//class ContextTests extends AsyncDOMSpec {
//  "Context" should "provide values to nested components" in withDebugLogging("context test") {
//    val container   = getContainer
//    val testContext = createContext("default")
//
//    case class ConsumerProps()
//
//    def Consumer(props: ConsumerProps) = {
//      val value = useContext(testContext)
//      logger.debug(
//        "Consumer rendering",
//        category = "Test",
//        Map(
//          "contextValue" -> value,
//          "instance"     -> ComponentInstance.current.map(_.id).getOrElse("none"),
//        ),
//      )
//      div(cls := "context-value", value)
//    }
//
//    case class ProviderProps(value: String)
//
//    def Provider(props: ProviderProps) = {
//      logger.debug(
//        "Provider rendering",
//        category = "Test",
//        Map("providedValue" -> props.value),
//      )
//      testContext.Provider <> testContext.ProviderProps(
//        value = props.value,
//        children = Consumer <> ConsumerProps(),
//      )
//    }
//
//    render(Provider <> ProviderProps("provided"), container)
//
//    eventually {
//      container.querySelector(".context-value").textContent shouldBe "provided"
//    }
//  }
//}
