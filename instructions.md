Core Goals
----------
- Create a lightweight React-like UI framework in Scala.js
- Support functional components with props
- Support hooks (useState, useEffect)
- Support virtual DOM diffing
- Make the API feel natural to Scala developers

Secondary Goals
---------------
- Keep good type safety
- Make component creation ergonomic
- Support common React patterns

Phase 1: Basic Component System
- Component creation and composition
- Props passing
- Event handling
- Basic state management (useState)
- Simple effects (useEffect)
- Virtual DOM with diffing
- Comprehensive debugging/logging system
    - Component creation
    - Render cycles
    - State changes
    - Diff operations
    - DOM updates

General Instructions
---------------------
- The project is coded in Scala.js.
- The project has the base package "io.github.edadma.fluxus".
- All test app should be styled using DaisyUI.
- Always keep design documents synchronized with changes in implementation, as issues are resolved.

  A well-documented pseudocode design would help in several ways:
  - Force clear thinking about the system architecture before coding
  - Serve as a reference during implementation
  - Help spot potential issues early
  - Make it easier to track where implementation deviates from design and why
  - Provide context for debugging decisions
