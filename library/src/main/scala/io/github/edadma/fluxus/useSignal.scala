package io.github.edadma.fluxus

import com.raquo.airstream.ownership.Owner
import com.raquo.airstream.core.{Observer, Transaction}
import com.raquo.airstream.state.Var

private class EffectOwner extends Owner {
  def cleanup(): Unit = killSubscriptions()
}

object SignalHook {
  // Initialize Airstream's transaction system
  Transaction { _ => () } // Empty transaction to initialize the system
}

def useSignal[A](signal: Var[A]): A = {
  // Ensure transaction system is initialized
  SignalHook

  val (value, setValue, _) = useState[A](signal.now())

  useEffect(
    () => {
      val owner    = new EffectOwner()
      val observer = Observer[A](setValue)

      // Observe the signal with the observer
      val subscription = signal.signal.addObserver(observer)(owner)

      // Cleanup on unmount or signal change
      () => {
        Transaction { _ =>
          subscription.kill()
          owner.cleanup()
        }
      }
    },
    Seq(signal),
  )

  value
}
