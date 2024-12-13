package io.github.edadma.fluxus.examples

import io.github.edadma.fluxus._

object DemoApp:
  def App: FluxusNode =
    div(
      cls := "min-h-screen bg-base-200",
      Navbar <> (),
      div(
        cls := "container mx-auto p-4",
        MainContent <> MainContentProps("Welcome to Fluxus"),
        CardGrid <> (),
      ),
      Footer <> (),
    )

  def Navbar: () => FluxusNode = () =>
    div(
      cls := "navbar bg-base-100 shadow-lg",
      div(
        cls := "flex-1",
        a(cls := "btn btn-ghost text-xl", "Fluxus Demo"),
      ),
      div(
        cls := "flex-none",
        ul(
          cls := "menu menu-horizontal px-1",
          li(a("About")),
          li(a("Contact")),
        ),
      ),
    )

  case class MainContentProps(title: String)

  def MainContent: MainContentProps => FluxusNode =
    case MainContentProps(title) =>
      div(
        cls := "hero bg-base-100 rounded-lg shadow-xl my-8",
        div(
          cls := "hero-content text-center",
          div(
            cls := "max-w-md",
            h1(cls := "text-5xl font-bold", title),
            p(
              cls := "py-6",
              "A minimalist React-like UI framework built with Scala.js.",
            ),
            button(cls := "btn btn-primary", "Get Started"),
          ),
        ),
      )

  case class FeatureCardProps(
      title: String,
      description: String,
      icon: String,
  )

  def FeatureCard: FeatureCardProps => FluxusNode =
    case FeatureCardProps(title, description, icon) =>
      div(
        cls := "card w-96 bg-base-100 shadow-xl",
        figure(
          cls := "px-10 pt-10",
          // Using Hero Icons through DaisyUI
          div(cls := s"hero-$icon-solid text-4xl"),
        ),
        div(
          cls := "card-body items-center text-center",
          h2(cls := "card-title", title),
          p(description),
          div(
            cls := "card-actions justify-end",
            button(cls := "btn btn-primary", "Learn More"),
          ),
        ),
      )

  // And update the card grid to use proper Hero Icon names
  def CardGrid: () => FluxusNode = () =>
    div(
      cls := "grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4 my-8",
      Vector(
        FeatureCardProps(
          "Components",
          "Build complex UIs from simple, reusable components",
          "cube", // Hero Icon name
        ),
        FeatureCardProps(
          "Functional",
          "Pure functional approach to UI development",
          "code-bracket", // Hero Icon name
        ),
        FeatureCardProps(
          "Type-Safe",
          "Leverage Scala's type system for reliable code",
          "shield-check", // Hero Icon name
        ),
      ).map(props => FeatureCard <> props),
    )

  def Footer: () => FluxusNode = () =>
    footer(
      cls := "footer footer-center p-10 bg-base-100 text-base-content rounded",
      div(
        nav(
          cls := "grid grid-flow-col gap-4",
          a(cls := "link link-hover", "About us"),
          a(cls := "link link-hover", "Contact"),
          a(cls := "link link-hover", "Documentation"),
          a(cls := "link link-hover", "GitHub"),
        ),
      ),
      div(
        p("Built with Fluxus and DaisyUI"),
      ),
    )
