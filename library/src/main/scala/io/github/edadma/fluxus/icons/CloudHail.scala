package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cloud-hail", JSImport.Default)
private object CloudHailIcon extends js.Array[js.Any]

def CloudHail(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CloudHailIcon, color, size)
