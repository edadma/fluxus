package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/webhook-off", JSImport.Default)
private object WebhookOffIcon extends js.Array[js.Any]

def WebhookOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(WebhookOffIcon, color, size)
