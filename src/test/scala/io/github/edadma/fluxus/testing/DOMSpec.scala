package io.github.edadma.fluxus.testing

import org.scalajs.dom
import org.scalajs.dom.{HTMLDocument, Window}
import org.scalatest.BeforeAndAfterEach
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("JSDOM")
class JSDOM(html: String) extends js.Object {
  def window: Window = js.native
}

trait DOMSpec extends AnyFlatSpec with Matchers with BeforeAndAfterEach {
  // Set up JSDOM
  val DOM                    = new JSDOM("""<!DOCTYPE html><div id="app"></div>""")
  val window: Window         = DOM.window
  val document: HTMLDocument = window.document

  def getContainer: dom.Element = {
    document.getElementById("app")
  }

  override def beforeEach(): Unit = {
    super.beforeEach()
    // Clear container before each test
    val container = getContainer
    while (container.firstChild != null) {
      container.removeChild(container.firstChild)
    }
  }

  // Helper to get text content and compare as string
  implicit class ElementOps(element: dom.Element) {
    def textContentString: String = element.textContent
  }
}
