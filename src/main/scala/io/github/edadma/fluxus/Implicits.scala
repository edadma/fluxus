package io.github.edadma.fluxus

import scala.language.implicitConversions

object Implicits:
  implicit class CaseClassComponentOps(val componentFunction: Product => FluxusNode) extends AnyVal:
    def apply(props: Product): FluxusNode = {
      println("CaseClassComponentOps.apply called") // Direct println to make sure
      FluxusLogger.Props.trace(
        "Converting case class props",
        Map(
          "originalType" -> props.getClass.getName,
          "isProduct"    -> props.isInstanceOf[Product],
        ),
      )

      val propsMap = props match {
        case p: Product =>
          val converted = productToProps(p)
          FluxusLogger.Props.trace(
            "Converted case class to map",
            Map(
              "result"     -> converted,
              "resultType" -> converted.getClass.getName,
            ),
          )
          converted
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
