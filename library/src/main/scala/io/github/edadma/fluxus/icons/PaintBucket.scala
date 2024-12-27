package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/paint-bucket", JSImport.Default)
private object PaintBucketIcon extends js.Array[js.Any]

def PaintBucket(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PaintBucketIcon, color, size)
