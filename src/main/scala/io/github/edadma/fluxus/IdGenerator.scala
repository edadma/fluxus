package io.github.edadma.fluxus

object IdGenerator {
  private var currentId: Int = 0

  def nextId(): String = {
    currentId += 1
    s"component-$currentId"
  }
}
