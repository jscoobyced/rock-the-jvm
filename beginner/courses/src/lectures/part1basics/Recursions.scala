package lectures.part1basics

import lectures.part1basics.Recursions.number

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

  var divider = 0

  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(number: Int, accumulator: Boolean): Boolean = {
      val ratio = n % number
      if (!accumulator) {
        divider = number + 1
        false
      }
      else if (number <= 1) true
      else isPrimeUntil(number - 1, ratio != 0 && accumulator)
    }

    if(n % 2 == 0) {
      divider = 2
      false
    }
    else isPrimeUntil(n / 2, true)
  }

  val number = 1003
  val isPrime: Boolean = isPrime(number)
  val isPrimeString: String = if (!isPrime) "not " else ""
  val isDivisible = if (!isPrime) s" It is divisible by ${divider}." else ""
  println(s"${number} is ${isPrimeString}a prime number.${isDivisible}")

}
