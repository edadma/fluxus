package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bean", JSImport.Default)
private object BeanIcon extends js.Array[js.Any]

def Bean(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BeanIcon, color, size)
