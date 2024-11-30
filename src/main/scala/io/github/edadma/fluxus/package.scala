package io.github.edadma.fluxus

type FluxusComponent = Product => (FluxusNode | Null)

type FC[Props <: Product] = Props => (FluxusNode | Null)

case class EmptyProps()

val emptyProps = EmptyProps()
