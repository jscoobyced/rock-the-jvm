package lectures.part2oop

object Exceptions extends App {

  val x: String = null

  def getInt(withException: Boolean): Int =
    if (withException) throw new RuntimeException("No Int for you.")
    else 42

  val potentialFail = try {
    getInt(false)
  } catch {
    case e: RuntimeException => println(s"Caught a ${e.getClass} exception: ${e.getMessage}")
  } finally {
    println("Finally")
  }

  println(potentialFail)

  class MyException extends Exception

  val exception = new MyException

  throw exception
}
