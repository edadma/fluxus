package io.github.edadma.fluxus.querystate.examples

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.querystate.{QueryState, useQueryParam}
import org.scalajs.dom

@main def run(): Unit = {
  // Initialize with default values
  QueryState.init(
    defaults = Map(
      "tab"    -> "home",
      "filter" -> "",
      "sort"   -> "newest",
    ),
  )

  // Render app to DOM
  render(App, "app")
}

// Main App component
def App: FluxusNode = {
  // Use query parameters in component
  val (activeTab, setActiveTab, _) = useQueryParam("tab", "home")

  div(
    cls := "container mx-auto p-4",

    // Header
    div(
      cls := "bg-blue-600 text-white p-4 mb-4 rounded",
      h1(cls := "text-2xl font-bold", "QueryState Demo"),
    ),

    // Navigation tabs
    div(
      cls := "flex border-b mb-4",
      button(
        cls     := s"px-4 py-2 ${if (activeTab == "home") "bg-blue-100 border-b-2 border-blue-600" else ""}",
        onClick := (() => setActiveTab("home")),
        "Home",
      ),
      button(
        cls     := s"px-4 py-2 ${if (activeTab == "products") "bg-blue-100 border-b-2 border-blue-600" else ""}",
        onClick := (() => setActiveTab("products")),
        "Products",
      ),
      button(
        cls     := s"px-4 py-2 ${if (activeTab == "about") "bg-blue-100 border-b-2 border-blue-600" else ""}",
        onClick := (() => setActiveTab("about")),
        "About",
      ),
    ),

    // Current URL display
    div(
      cls := "bg-gray-100 p-2 mb-4 rounded font-mono text-sm",
      p("Current URL:"),
      code(dom.window.location.href),
    ),

    // Tab content
    div(
      cls := "border p-4 rounded",
      activeTab match {
        case "products" => ProductsTab <> ()
        case "about"    => AboutTab <> ()
        case _          => HomeTab <> ()
      },
    ),

    // Reset button
    div(
      cls := "mt-4",
      button(
        cls     := "bg-red-500 text-white px-4 py-2 rounded",
        onClick := (() => QueryState.reset()),
        "Reset URL State",
      ),
    ),

    // Footer
    div(
      cls := "mt-8 pt-4 border-t text-center text-gray-500",
      p("Try navigating with the browser's back/forward buttons"),
    ),
  )
}

// Home tab content
def HomeTab = () => {
  div(
    h2(cls := "text-xl font-bold mb-4", "Welcome to QueryState Demo"),
    p(
      "This demo shows how to synchronize application state with URL query parameters.",
      br(),
      "Try clicking the tabs above and notice how the URL changes.",
    ),
    div(
      cls := "mt-4 p-4 bg-yellow-100 rounded",
      h3(cls := "font-bold", "Features:"),
      ul(
        cls := "list-disc pl-5",
        li("URL reflects application state"),
        li("Browser back/forward navigation works"),
        li("Shareable URLs with current state"),
        li("No full page reloads"),
      ),
    ),
  )
}

// Products tab content
def ProductsTab = () => {
  // Use query parameters for filter and sort
  val (filter, setFilter, _) = useQueryParam("filter", "")
  val (sort, setSort, _)     = useQueryParam("sort", "newest")

  // Product data
  val products = Seq(
    ("1", "Laptop", "Electronics", 999.99),
    ("2", "Desk Chair", "Furniture", 199.50),
    ("3", "Coffee Maker", "Kitchen", 49.99),
    ("4", "Headphones", "Electronics", 149.99),
    ("5", "Bookshelf", "Furniture", 89.99),
  )

  // Apply filter
  val filteredProducts = if (filter.isEmpty) {
    products
  } else {
    products.filter(_._3.toLowerCase == filter.toLowerCase)
  }

  // Apply sorting
  val sortedProducts = sort match {
    case "price-low"  => filteredProducts.sortBy(_._4)
    case "price-high" => filteredProducts.sortBy(-_._4)
    case _            => filteredProducts // "newest" - keep default order
  }

  div(
    h2(cls := "text-xl font-bold mb-4", "Products"),

    // Filter and sort controls
    div(
      cls := "flex flex-wrap gap-4 mb-4",
      div(
        label(cls := "block text-sm font-medium text-gray-700", "Filter by Category"),
        select(
          cls   := "mt-1 block w-full rounded-md border-gray-300 shadow-sm p-2",
          value := filter,
          onChange := ((e: dom.Event) =>
            setFilter(e.target.asInstanceOf[dom.html.Select].value)
          ),
          option(value := "", "All Categories"),
          option(value := "electronics", "Electronics"),
          option(value := "furniture", "Furniture"),
          option(value := "kitchen", "Kitchen"),
        ),
      ),
      div(
        label(cls := "block text-sm font-medium text-gray-700", "Sort by"),
        select(
          cls   := "mt-1 block w-full rounded-md border-gray-300 shadow-sm p-2",
          value := sort,
          onChange := ((e: dom.Event) =>
            setSort(e.target.asInstanceOf[dom.html.Select].value)
          ),
          option(value := "newest", "Newest"),
          option(value := "price-low", "Price: Low to High"),
          option(value := "price-high", "Price: High to Low"),
        ),
      ),
    ),

    // Product list
    div(
      cls := "grid grid-cols-1 gap-4",
      sortedProducts.map { case (id, name, category, price) =>
        div(
          key := id,
          cls := "border p-4 rounded shadow",
          div(cls := "font-bold", name),
          div(cls := "text-sm text-gray-500", s"Category: $category"),
          div(cls := "mt-2 font-bold text-blue-600", f"$$${price}%.2f"),
        )
      },
    ),

    // Empty state
    if (sortedProducts.isEmpty) {
      div(
        cls := "text-center p-8 text-gray-500",
        "No products match your filter criteria",
      )
    } else null,
  )
}

// About tab content
def AboutTab = () => {
  div(
    h2(cls := "text-xl font-bold mb-4", "About QueryState"),
    p(cls  := "mb-4", "QueryState is a lightweight library for Fluxus applications that:"),
    ul(
      cls := "list-disc pl-5 mb-4",
      li("Synchronizes application state with URL query parameters"),
      li("Maintains state across page refreshes"),
      li("Enables shareable URLs with preserved state"),
      li("Integrates with browser history for back/forward navigation"),
      li("Provides a simple hook-based API for components"),
    ),
    div(
      cls := "bg-gray-100 p-4 rounded",
      h3(cls := "font-bold mb-2", "Usage example:"),
      pre(
        code(
          "// Initialize with defaults\n" +
            "QueryState.init(\n" +
            "  defaults = Map(\n" +
            "    \"tab\" -> \"home\",\n" +
            "    \"filter\" -> \"\"\n" +
            "  )\n" +
            ")\n\n" +
            "// In a component\n" +
            "val (filter, setFilter) = useQueryParam(\"filter\", \"\")\n\n" +
            "// Update parameter\n" +
            "setFilter(\"new-value\")",
        ),
      ),
    ),
  )
}
