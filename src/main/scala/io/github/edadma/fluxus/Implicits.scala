package io.github.edadma.fluxus

import scala.language.implicitConversions

object Implicits:
  implicit class CaseClassComponentOps[P](val componentFunction: P => FluxusNode) extends AnyVal:
    def apply(props: P): FluxusNode = {
      val propsMap = props match {
        case p: Product => productToProps(p)
        case _          => Map.empty[String, Any]
      }
      ComponentNode(
        key = propsMap.get("key").map(_.toString),
        componentFunction = componentFunction.asInstanceOf[Props => FluxusNode],
        props = propsMap,
      )
    }

  implicit class NoPropsComponentOps(val componentFunction: () => FluxusNode) extends AnyVal:
    def apply(): FluxusNode =
      ComponentNode(
        key = None,
        componentFunction = (_ => componentFunction()),
        props = Map.empty,
      )

  private def productToProps(p: Product): Map[String, Any] =
    p.productElementNames.zip(p.productIterator).toMap

  implicit def funcToUpdate[T](func: T => T): Update[T] = Update(func)
