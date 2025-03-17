package io.github.edadma.fluxus.testing

import io.github.edadma.fluxus._
import org.scalajs.dom

class I18nTests extends AsyncDOMSpec {
  "I18n" should "support simple translations" in {
    val container = getContainer

    // Define test translations
    val enYaml = """
                   |greeting: Hello
                   |farewell: Goodbye
                   |user:
                   |  welcome: "Welcome, {name}!"""".stripMargin

    val frYaml = """
                   |greeting: Bonjour
                   |farewell: Au revoir
                   |user:
                   |  welcome: "Bienvenue, {name}!"""".stripMargin

    // Load translations
    I18n.loadTranslation("en", enYaml)
    I18n.loadTranslation("fr", frYaml)
    I18n.setLanguage("en")

    // Test component that uses translations
    def TranslationTestComponent = () => {
      val t = useTranslation()

      div(
        cls := "i18n-test",
        h1(t("greeting")),
        p(t("user.welcome", "name" -> "John")),
        button(
          cls     := "switch-lang",
          onClick := (() => I18n.setLanguage("fr")),
          "Switch to French",
        ),
      )
    }

    // Render component
    render(TranslationTestComponent <> (), container)

    // Verify initial English translation
    eventually {
      container.querySelector("h1").textContent shouldBe "Hello"
      container.querySelector("p").textContent shouldBe "Welcome, John!"
    }
      .flatMap { _ =>
        // Click button to switch language
        click(container.querySelector(".switch-lang"))

        // Verify French translation
        eventually {
          container.querySelector("h1").textContent shouldBe "Bonjour"
          container.querySelector("p").textContent shouldBe "Bienvenue, John!"
        }
      }
  }
}
