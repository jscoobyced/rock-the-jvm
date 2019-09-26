package lectures.part1basics

import scala.annotation.tailrec

object Recursions extends App {

  def factorial(n: Int): Int =
    if (n <= 1) n
    else {
      println("Computing factorial of " + n + " - I need to compute factorial of " + (n - 1))
      val result = n * factorial(n - 1)
      println("Computed factorial of " + n)

      result
    }

  // println(factorial(10))

  @tailrec
  def anotherFactorial(n: Int, accumulator: BigDecimal): BigDecimal = {
    if (n <= 1) accumulator
    else anotherFactorial(n - 1, n * accumulator)
  }

  // println(anotherFactorial(5000, 1))

  def concat(s: String, n: Int, r: String): String = {
    if (n < 1) r
    else concat(s, n - 1, r + s)
  }

  println(concat("hello_", 5, ""))

  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(t: Int, r: Boolean): Boolean =
      if (!r) false
      else if (t <= 1) true
      else isPrimeUntil(t - 1, n % t != 0 && r)

    isPrimeUntil(n / 2, true)
  }

  println(isPrime(353))

}
