package io.github.edadma.fluxus

def useCallback[T](callback: T, deps: Seq[Any]): T = useMemo(() => callback, deps)
