package io.github.edadma.fluxus

type FluxusComponent = Props => (FluxusNode | Null)

type FC[Props] = Props => (FluxusNode | Null)

trait HasClassName:
  val className: String

trait HasKey:
  val key: String
