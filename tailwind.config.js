// tailwind.config.js

module.exports = {
  // Specify the paths to all of your template files
  content: [
    './examples/target/scala-3.6.2/examples-fastopt/**/*.js', // Your Scala.js source files
    './index.html',         // Your HTML files
  ],
  daisyui: {
      themes: ["light", "dark", "night", "cupcake"],
  },
  // Extend the default Tailwind CSS configuration
  theme: {
    extend: {
      fontFamily: {
        'seven-segment': ['SevenSegment', 'sans-serif'],
        'dseg7modern': ['DSEG7Modern', 'sans-serif'],
        'dseg7modernmini': ['DSEG7ModernMini', 'sans-serif'],
        'poppins': ['Poppins', 'sans-serif'],
      }
    },
  },
  // Add DaisyUI as a plugin
  plugins: [require("@tailwindcss/typography"), require('daisyui')],
};
