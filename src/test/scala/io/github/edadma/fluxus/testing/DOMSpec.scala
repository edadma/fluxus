package io.github.edadma.fluxus.testing

import org.scalajs.dom
import org.scalatest.BeforeAndAfterEach
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import scala.scalajs.js
import js.annotation.JSImport
import js.Dynamic.{global => g}

@JSImport("jsdom", "JSDOM")
@js.native
class JSDOM(html: String) extends js.Object {
  val window: js.Dynamic = js.native
}

trait DOMSpec extends AnyFlatSpec with Matchers with BeforeAndAfterEach {
//  val DOM = new JSDOM("<!doctype html><html><body></body></html>")
//
//  g.window = DOM.window
//  g.document = DOM.window.document
//
//  def getContainer: dom.Element = {
//    dom.window.document.getElementById("app")
//  }
//
//  override def beforeEach(): Unit = {
//    super.beforeEach()
//    // Clear container before each test
//    val container = getContainer
//    while (container.firstChild != null) {
//      container.removeChild(container.firstChild)
//    }
//  }
//
//  // Helper to get text content and compare as string
//  implicit class ElementOps(element: dom.Element) {
//    def textContentString: String = element.textContent
//  }
}
