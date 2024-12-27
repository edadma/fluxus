package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cloud-lightning", JSImport.Default)
private object CloudLightningIcon extends js.Array[js.Any]

def CloudLightning(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CloudLightningIcon, color, size)
