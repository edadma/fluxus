package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/voicemail", JSImport.Default)
private object VoicemailIcon extends js.Array[js.Any]

def Voicemail(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(VoicemailIcon, color, size)
