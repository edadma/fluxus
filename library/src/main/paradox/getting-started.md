# Getting Started

This guide will help you set up a new Fluxus project and build your first component.

## Prerequisites

- JDK 11 or higher
- sbt 1.5.0 or higher
- Node.js 14.0.0 or higher (for development server)

## Project Setup

The easiest way to start a new Fluxus project is to use the template repository:

```bash
git clone https://github.com/edadma/fluxus-template my-fluxus-app
cd my-fluxus-app
```

Alternatively, you can add Fluxus to an existing Scala.js project:

### sbt Setup

Add the following to your `build.sbt`:

```scala
enablePlugins(ScalaJSPlugin)

// For Scala 3
scalaVersion := "3.6.4"

// Add Fluxus dependency
libraryDependencies += "io.github.edadma" %%% "fluxus" % "0.0.30"

// Optional: Configure Scala.js settings
scalaJSUseMainModuleInitializer := true
scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) }
```

### HTML Setup

Create an `index.html` file in your project root:

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Fluxus App</title>
</head>
<body>
    <div id="app"></div>
    <script type="module" src="main.js"></script>
</body>
</html>
```

### JS Entry Point

Create a `main.js` file in your project root:

```javascript
import 'scalajs:main.js'
```

## Building Your First Component

Create a new Scala file `src/main/scala/MyApp.scala`:

```scala
import io.github.edadma.fluxus.*

@main def run(): Unit = {
  render(App, "app")
}

def App: FluxusNode = {
  val (count, setCount, _) = useState(0)
  
  div(
    h1("My First Fluxus App"),
    p(s"You clicked the button $count times"),
    button(
      onClick := (() => setCount(count + 1)),
      "Click me"
    )
  )
}
```

## Running the Application

Start the development server:

```bash
npm run dev
```

This will compile your Scala.js code and start a development server. Open your browser to the URL shown in the terminal (usually http://localhost:5173).

## Development Workflow

1. Write your components in Scala using Fluxus
2. Save the files to trigger automatic recompilation
3. See changes reflected in the browser

## Next Steps

Now that you have a basic Fluxus application running, you can:

- Learn about @ref[Hooks](hooks/index.md) for state and effects
- Explore component composition patterns
- Add styling with CSS or a CSS-in-JS library
- Integrate with external APIs using `useFetch`