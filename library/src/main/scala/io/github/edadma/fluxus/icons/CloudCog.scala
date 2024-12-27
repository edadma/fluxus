package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cloud-cog", JSImport.Default)
private object CloudCogIcon extends js.Array[js.Any]

def CloudCog(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CloudCogIcon, color, size)
