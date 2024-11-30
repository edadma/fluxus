package io.github.edadma.fluxus // Define the package namespace for the Fluxus framework

// In element.scala or a new components.scala
def component[P <: Product](componentFunction: FC[P])(props: P): FluxusNode =
  val parentInstance = Option(RenderContext.currentInstance)
  val node = ComponentNode(
    key = props match {
      case k: KeyedProps => Some(k.key)
      case _             => None
    },
    componentFunction = (p: Product) => componentFunction(p.asInstanceOf[P]),
    props = props,
  )

  // When instance is created, connect it to parent
  node.instance.foreach { instance =>
    parentInstance.foreach { parent =>
      parent.children += instance
    }
  }

  node
