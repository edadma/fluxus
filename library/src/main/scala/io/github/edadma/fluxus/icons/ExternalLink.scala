package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/external-link", JSImport.Default)
private object ExternalLinkIcon extends js.Array[js.Any]

def ExternalLink(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ExternalLinkIcon, color, size)
