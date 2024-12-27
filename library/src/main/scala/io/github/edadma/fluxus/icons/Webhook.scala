package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/webhook", JSImport.Default)
private object WebhookIcon extends js.Array[js.Any]

def Webhook(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(WebhookIcon, color, size)
