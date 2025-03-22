package io.github.edadma.fluxus.examples

import io.github.edadma.fluxus.*
import scala.scalajs.js

object UseMemoDemo {
  def App: FluxusNode = {
    div(
      cls := "min-h-screen bg-base-200 p-8",
      div(
        cls := "card bg-base-100 shadow-xl max-w-2xl mx-auto",
        div(
          cls := "card-body",
          h2(cls := "card-title text-center", "useMemo Demo - Fibonacci Calculator"),
          FibonacciCalculator <> (),
        ),
      ),
    )
  }

  def FibonacciCalculator: () => FluxusNode = () => {
    // State for the input number
    val (number, setNumber, _) = useState(35)

    // State for an unrelated counter to demonstrate re-renders
    val (counter, setCounter, _) = useState(0)

    // State to track calculation time
    val (calcTime, setCalcTime, _) = useState(0.0)

    // Calculate Fibonacci with memoization
    val fibResult = useMemo(
      () => {
        val startTime = js.Date.now()

        // Recursive Fibonacci - intentionally inefficient to demonstrate memoization
        def fib(n: Int): BigInt = {
          if (n <= 1) n
          else fib(n - 1) + fib(n - 2)
        }

        val result  = fib(number)
        val endTime = js.Date.now()

        // Store calculation time
        setCalcTime(endTime - startTime)

        result.toString
      },
      Seq(number), // Only recalculate when number changes
    )

    div(
      cls := "space-y-6",

      // Input for Fibonacci number
      div(
        cls := "form-control",
        label(cls := "label", "Enter a number (25-40 recommended)"),
        input(
          cls    := "input input-bordered",
          typ    := "number",
          min    := "1",
          max    := "40",
          value_ := number.toString,
          onInput := ((e: org.scalajs.dom.Event) => {
            val value = e.target.asInstanceOf[org.scalajs.dom.html.Input].value
            if (value.nonEmpty) {
              setNumber(value.toInt)
            }
          }),
        ),
      ),

      // Results display
      div(
        cls := "stats shadow",
        div(
          cls := "stat",
          div(cls := "stat-title", "Fibonacci Result"),
          div(cls := "stat-value text-primary", fibResult),
          div(cls := "stat-desc", s"Calculation time: ${calcTime.toInt}ms"),
        ),
      ),

      // Unrelated counter to demonstrate memoization
      div(
        cls := "mt-8 space-y-2",
        div(
          cls := "alert alert-info",
          "This counter doesn't affect the Fibonacci calculation",
        ),
        div(
          cls := "flex items-center justify-between",
          span(s"Counter: $counter"),
          button(
            cls     := "btn btn-primary",
            onClick := (() => setCounter(counter + 1)),
            "Increment Counter",
          ),
        ),
      ),

      // Explanation
      div(
        cls := "mt-8 p-4 bg-base-200 rounded-lg",
        h3(cls := "text-lg font-bold", "How useMemo Works Here"),
        p(
          "The Fibonacci calculation is expensive and uses useMemo to cache results. " +
            "When you change the input number, a new result is calculated and the time is displayed. " +
            "When you click the counter button, the component re-renders but doesn't recalculate Fibonacci " +
            "because the number hasn't changed.",
        ),
      ),
    )
  }
}
