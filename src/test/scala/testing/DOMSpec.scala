package io.github.edadma.fluxus.testing

import org.scalatest.BeforeAndAfterEach
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("JSDOM")
class JSDOM(html: String) extends js.Object {
  def window: js.Dynamic = js.native
}

trait DOMSpec extends AnyFlatSpec with Matchers with BeforeAndAfterEach {
  // Set up JSDOM
  val dom      = new JSDOM("""<!DOCTYPE html><div id="app"></div>""")
  val document = dom.window.document

  override def beforeEach(): Unit = {
    super.beforeEach()
    // Clear container before each test
    val container = document.getElementById("app")
    while (container.firstChild != null) {
      container.removeChild(container.firstChild)
    }
  }
}
