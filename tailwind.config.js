// tailwind.config.js

module.exports = {
  // Specify the paths to all of your template files
  content: [
    './target/scala-3.5.2/fluxus-fastopt/**/*.js', // Your Scala.js source files
    './public/index.html',         // Your HTML files
  ],
  daisyui: {
      themes: ["light", "dark", "night", "cupcake"],
  },
  // Extend the default Tailwind CSS configuration
  theme: {
    extend: {
      fontFamily: {
        'custom': ['seven-segment', 'sans-serif'],
      }
    },
  },
  // Add DaisyUI as a plugin
  plugins: [require("@tailwindcss/typography"), require('daisyui')],
};
