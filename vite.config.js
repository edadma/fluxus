import { defineConfig } from "vite";
import scalaJSPlugin from "@scala-js/vite-plugin-scalajs";
import tailwindcss from '@tailwindcss/vite'

export default defineConfig({
  plugins: [scalaJSPlugin({
    cwd: ".",  // Root directory of the project
    projectID: "examples" // The SBT project ID
  }), tailwindcss()],
});