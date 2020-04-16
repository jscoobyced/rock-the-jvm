package lectures.part4pm

object PatternsEverywhere extends App {

  // Similar to a match on the exception
  try {
    //
  } catch {
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => "npe"
    case _ => "something else"
  }

  // generators are also based on PM
  val list = List(1, 2, 3, 4)
  val evenOnes = for {
    x <- list if x % 2 == 0
  } yield 10 * x

  val tuple = (1, 2, 3)
  val (a, b, c) = tuple

  val head :: tail = list
  println(head)
  println(tail)

  val mappedList = list.map {
    case v if v >= 2 => v + " is there"
    case 1 => "one"
    case _ => "something else"
  }
  // (partial function literal) is equivalent to
  val mappedList2 = list.map { x =>
    x match {
      case v if v >= 2 => v + " is there"
      case 1 => "one"
      case _ => "something else"
    }
  }
}
