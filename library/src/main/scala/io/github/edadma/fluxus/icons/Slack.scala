package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/slack", JSImport.Default)
private object SlackIcon extends js.Array[js.Any]

def Slack(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SlackIcon, color, size)
