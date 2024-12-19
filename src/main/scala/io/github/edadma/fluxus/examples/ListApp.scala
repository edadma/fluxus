package io.github.edadma.fluxus.examples

import io.github.edadma.fluxus.*

case class ListItem(id: Int, text: String)
case class ListProps()

object ListApp:
  def App: FluxusNode = ListApp <> ListProps()

  def ListApp(props: ListProps): FluxusNode = {
    val (items, setItems)         = useState(Vector[ListItem]())
    val (showStats, setShowStats) = useState(false)

    def handleAdd() =
      setItems(prev => prev :+ ListItem(prev.length, s"Item ${prev.length + 1}"))

    div(
//      // Add button
//      button(
//        onClick := (() => handleAdd()),
//        "Add Item",
//      ),

      // Stats toggle
      button(
        onClick := (() => setShowStats(!_)),
        "Toggle Stats",
      ),

      // Stats display
      Option.when(showStats)(
        div(
          s"Total items: ${items.length}",
        ),
      ),
      div(),
//      // List display
//      div(
//        items.map(item =>
//          div(
//            key := item.id.toString,
//            item.text,
//          ),
//        ),
//      ),
    )
  }
