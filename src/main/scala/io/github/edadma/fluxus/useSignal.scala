package io.github.edadma.fluxus

import com.raquo.airstream.ownership.Owner
import com.raquo.airstream.core.Observer
import com.raquo.airstream.state.Var

private class EffectOwner extends Owner {
  def cleanup(): Unit = killSubscriptions()
}

def useSignal[A](signal: Var[A]): A = {
  val (value, setValue, _) = useState[A](signal.now())

  useEffect(
    () => {
      val owner    = new EffectOwner()
      val observer = Observer[A](setValue)

      // Observe the signal with the observer
      signal.signal.addObserver(observer)(owner)

      // Cleanup on unmount or signal change
      () => owner.cleanup()
    },
    Seq(signal),
  )

  value
}
