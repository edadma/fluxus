package io.github.edadma.fluxus

import scala.language.implicitConversions

object Implicits:
  implicit class CaseClassComponentOps(val componentFunction: FluxusComponent) extends AnyVal:
    def apply(props: Product): FluxusNode = {
      println("CaseClassComponentOps.apply called") // Direct println to make sure
      FluxusLogger.Props.trace(
        "Converting case class props",
        Map(
          "originalType" -> props.getClass.getName,
          "isProduct"    -> props.isInstanceOf[Product],
        ),
      )

      val key = props.productElementNames.zip(props.productIterator)
        .find(_._1 == "key")
        .map(_._2.toString)

      ComponentNode(
        key = key,
        componentFunction = componentFunction,
        props = props,
      )
    }

  implicit class NoPropsComponentOps(val componentFunction: () => FluxusNode) extends AnyVal:
    def apply(): FluxusNode =
      ComponentNode(
        key = None,
        componentFunction = (_ => componentFunction()),
        props = emptyProps,
      )

  implicit def funcToUpdate[T](func: T => T): Update[T] = Update(func)
