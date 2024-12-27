package io.github.edadma.fluxus.testing

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.core.{ComponentInstance, createDOM, diff, reconcile, InsertNode, ReplaceNode, UpdateText}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import pprint.pprintln

class DiffTests extends AnyFlatSpec with Matchers {
  "diff" should "handle simple text changes" in {
    val oldTree = TextNode("hello", None, None)
    val newTree = TextNode("world", None, None)

    val ops = diff(Some(oldTree), Some(newTree))

    ops should contain theSameElementsInOrderAs Seq(
      UpdateText(oldTree, "world"),
    )
  }

  it should "handle node type changes" in {
    val oldTree = div("hello")
    val newTree = span("hello")

    val ops = diff(Some(oldTree), Some(newTree))

    ops should contain theSameElementsInOrderAs Seq(
      ReplaceNode(oldTree, newTree),
    )
  }
}
