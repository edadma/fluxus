package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cloud-drizzle", JSImport.Default)
private object CloudDrizzleIcon extends js.Array[js.Any]

def CloudDrizzle(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CloudDrizzleIcon, color, size)
