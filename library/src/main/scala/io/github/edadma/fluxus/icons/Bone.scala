package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bone", JSImport.Default)
private object BoneIcon extends js.Array[js.Any]

def Bone(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BoneIcon, color, size)
