package io.github.edadma.fluxus

import org.scalajs.dom
import org.scalajs.dom.Element

/** Creates a RawNode containing the HTML parsed from the provided string. This allows injecting raw HTML into the
  * component tree.
  *
  * @param html
  *   The raw HTML string to parse
  * @param sanitize
  *   Whether to sanitize the HTML (defaults to true)
  * @return
  *   A FluxusNode that can be included in component trees
  */
def rawHtml(html: String, sanitize: Boolean = true): FluxusNode = {
  // Create a temporary div to parse the HTML
  val tempDiv = dom.document.createElement("div")

  // Set the inner HTML (this is where parsing happens)
  if (sanitize) {
    // Basic sanitization (in a real implementation, you'd want a more robust sanitizer)
    // This is just a simple example
    val sanitized = html
      .replaceAll("<script\\b[^<]*(?:(?!</script>)<[^<]*)*</script>", "") // Remove script tags
      .replaceAll("on\\w+\\s*=\\s*\"[^\"]*\"", "")                        // Remove on* event handlers
    tempDiv.innerHTML = sanitized
  } else {
    tempDiv.innerHTML = html
  }

  // If there's just one root element, use it directly
  if (tempDiv.childNodes.length == 1 && tempDiv.firstChild.nodeType == dom.Node.ELEMENT_NODE) {
    RawNode(tempDiv.firstChild.asInstanceOf[Element])
  } else {
    // If there are multiple nodes, keep them wrapped in the div
    RawNode(tempDiv)
  }
}
