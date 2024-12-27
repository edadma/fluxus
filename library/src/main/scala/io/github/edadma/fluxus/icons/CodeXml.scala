package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/code-xml", JSImport.Default)
private object CodeXmlIcon extends js.Array[js.Any]

def CodeXml(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CodeXmlIcon, color, size)
