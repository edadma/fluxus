# Getting Started with Fluxus

This guide will walk you through setting up a new Fluxus project and creating your first component.

## Prerequisites

- [Scala](https://scala-lang.org/) (3.6.0 or higher)
- [sbt](https://www.scala-sbt.org/) (1.8.0 or higher)
- [Node.js](https://nodejs.org/) (16.x or higher)

## Project Setup

The easiest way to get started is to use the [fluxus-template](https://github.com/edadma/fluxus-template) repository:

```bash
git clone https://github.com/edadma/fluxus-template.git my-fluxus-app
cd my-fluxus-app
npm install
```

### Manual Setup

If you prefer to set up your project manually, create a new SBT project with Scala.js:

#### build.sbt
```scala
ThisBuild / scalaVersion := "3.6.4"
ThisBuild / organization := "com.example"

lazy val root = project
  .in(file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "my-fluxus-app",
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies ++= Seq(
      "io.github.edadma" %%% "fluxus" % "0.0.30"
    )
  )
```

#### project/plugins.sbt
```scala
addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.13.1")
```

## Your First Component

Create a simple application with a counter component:

```scala
package example

import io.github.edadma.fluxus.*

object MyApp {
  def main(args: Array[String]): Unit = {
    render(App, "app")
  }

  def App: FluxusNode = {
    val (count, setCount, _) = useState(0)
    
    div(
      cls := "container",
      h1("My First Fluxus App"),
      p(s"Count: $count"),
      button(
        onClick := (() => setCount(count + 1)),
        "Increment"
      )
    )
  }
}
```

Create an `index.html` file:

```html
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>My Fluxus App</title>
    <style>
      .container {
        max-width: 800px;
        margin: 0 auto;
        padding: 2rem;
        font-family: system-ui, sans-serif;
      }
      button {
        padding: 0.5rem 1rem;
        background: #0077cc;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
      }
    </style>
  </head>
  <body>
    <div id="app"></div>
    <script src="./target/scala-3.6.4/my-fluxus-app-fastopt.js"></script>
  </body>
</html>
```

## Development Workflow

1. Start the SBT server:
   ```bash
   sbt
   ```

2. Inside the SBT console, start continuous compilation:
   ```
   ~fastLinkJS
   ```

3. Open the `index.html` file in your browser.

## Using with Vite (Recommended)

For a better development experience with hot reloading, set up Vite with the Scala.js plugin:

### vite.config.js
```javascript
import { defineConfig } from "vite";
import scalaJSPlugin from "@scala-js/vite-plugin-scalajs";

export default defineConfig({
  plugins: [scalaJSPlugin({
    cwd: ".",
    projectID: "root"
  })],
});
```

### package.json
```json
{
  "name": "my-fluxus-app",
  "version": "0.0.1",
  "type": "module",
  "scripts": {
    "dev": "vite",
    "build": "vite build",
    "preview": "vite preview"
  },
  "devDependencies": {
    "@scala-js/vite-plugin-scalajs": "^1.0.0",
    "vite": "^4.3.9"
  }
}
```

Now you can run the development server with:

```bash
npm run dev
```

## Next Steps

Now that you have your first Fluxus application running, explore the following topics:

- @ref[Core Concepts](core-concepts/index.md) to understand the Fluxus architecture
- @ref[Hooks](hooks/index.md) for state management and side effects
