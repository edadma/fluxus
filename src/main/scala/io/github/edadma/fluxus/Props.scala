package io.github.edadma.fluxus

// Type alias for component properties (props)
type Props = Map[String, Any]

// Helper function to create props
def props(pairs: (String, Any)*): Props = Map(pairs*)
