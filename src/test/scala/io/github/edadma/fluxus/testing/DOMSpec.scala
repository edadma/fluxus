package io.github.edadma.fluxus.testing

import io.github.edadma.fluxus.core.hooks.Hooks
import io.github.edadma.fluxus.logging.Logger
import org.scalajs.dom
import org.scalatest.BeforeAndAfterEach
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.scalajs.js
import js.annotation.JSImport

@JSImport("jsdom", "JSDOM")
@js.native
class JSDOM(html: String) extends js.Object {
  val window: dom.Window = js.native
}

trait DOMSpec extends BaseTest {
  val jsdom = new JSDOM("""<!doctype html><html><body><div id="app"></div></body></html>""")

  js.Dynamic.global.global.window = jsdom.window
  js.Dynamic.global.global.document = jsdom.window.document

  def getContainer: dom.Element = dom.document.getElementById("app")

  override def beforeEach(): Unit = {
    super.beforeEach()
    // Clear container before each test
    val container = getContainer
    while (container.firstChild != null) {
      container.removeChild(container.firstChild)
    }
  }
}
