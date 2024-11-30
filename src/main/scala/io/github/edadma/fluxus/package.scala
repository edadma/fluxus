package io.github.edadma.fluxus

type FluxusComponent = Product => (FluxusNode | Null)

type FC[Props <: Product] = Props => (FluxusNode | Null)

trait HasClassName:
  val className: String

trait HasKey:
  val key: String
