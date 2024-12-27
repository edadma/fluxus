package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chef-hat", JSImport.Default)
private object ChefHatIcon extends js.Array[js.Any]

def ChefHat(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChefHatIcon, color, size)
