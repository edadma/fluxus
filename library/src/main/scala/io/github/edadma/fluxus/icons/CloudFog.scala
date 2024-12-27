package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cloud-fog", JSImport.Default)
private object CloudFogIcon extends js.Array[js.Any]

def CloudFog(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CloudFogIcon, color, size)
