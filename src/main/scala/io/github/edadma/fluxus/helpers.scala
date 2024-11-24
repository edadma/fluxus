package io.github.edadma.fluxus

def button(args: ElementArg*): FluxusNode = element("button")(args*)
def div(args: ElementArg*): FluxusNode    = element("div")(args*)
def span(args: ElementArg*): FluxusNode   = element("span")(args*)
def h1(args: ElementArg*): FluxusNode     = element("h1")(args*)
def p(args: ElementArg*): FluxusNode      = element("p")(args*)
def ul(args: ElementArg*): FluxusNode     = element("ul")(args*)
def li(args: ElementArg*): FluxusNode     = element("li")(args*)