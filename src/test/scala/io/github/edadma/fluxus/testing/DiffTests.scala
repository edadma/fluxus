package io.github.edadma.fluxus.testing

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.core.DOMOperation.{InsertNode, Replace, UpdateText}
import io.github.edadma.fluxus.core.{ComponentInstance, createDOM, diff, reconcile}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import pprint.pprintln

class DiffTests extends AnyFlatSpec with Matchers {
  "diff" should "handle optional content insertion correctly" in {
    // Initial tree - no optional content
    val oldTree = div(
      button(onClick := (() => ()), "Toggle"),
      div("last"),
    )

    // New tree - with optional content inserted
    val newTree = div(
      button(onClick := (() => ()), "Toggle"),
      div("toggled"),
      div("last"),
    )

    val ops = diff(Some(oldTree), Some(newTree))

    pprintln(ops)
    // Just insert the middle div, preserve the last div
    ops should contain theSameElementsInOrderAs Seq(
      InsertNode(div("toggled"), Some(1)),
    )
  }

  it should "handle simple text changes" in {
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
      Replace(oldTree, newTree),
    )
  }
}
