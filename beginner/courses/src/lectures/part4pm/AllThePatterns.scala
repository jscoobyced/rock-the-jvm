package lectures.part4pm

import exercises.part3fp.{Cons, Empty, MyList}

object AllThePatterns extends App {

  // Constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "a number"
    case "Scala" => "THE Scala"
    case true => "The Truth"
    case AllThePatterns => "A singleton object"
    case something => s"Found $something"
    case _ => "Whatever"
  }

  // Tuples
  val aTuple = (1, 2)
  val matchATuple = aTuple match {
    case (1, 1) => "Something"
    case (something, 2) => s"Found $something"
  }

  // Nested Tuples
  val nestedTuple = (1, (2, 3))
  val matchNestedTuple = nestedTuple match {
    case (_, (2, v)) => s"Wow we've found $v"
  }

  // Case class
  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
  val matchList = aList match {
    case Empty =>
    case Cons(head1, Cons(head2, tail2)) =>
  }

  // List pattern
  val aStandardList = List(1, 2, 3, 42)
  val standardListMatching = aStandardList match {
    case List(1, _, _, _) => // extractor
    case List(1, _*) => // List of arbitrary length
    case 1 :: List(_) => // infix pattern
    case List(_) :+ 42 => // infix pattern
  }

  // type specifier
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] =>
    case _ =>
  }

  // Name binding
  val nameBindingMatch = aList match {
    case noneEmptyList@Cons(_, _) => noneEmptyList
    case Cons(1, remainder@Cons(2, _)) => remainder
  }

  // Multi-pattern
  val multiPattern = aList match {
    case Empty | Cons(0, _) => // Compound or multi pattern
    case _ =>
  }

  // If guard
  val secondElementSpecial = aList match {
    case Cons(_, Cons(specialElement, _)) if specialElement > 3 =>
    case _ =>
  }

  val numbers = List(1, 2, 3)
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => "a list of String"
    case listOfNumbers: List[Int] => "a list of numbers"
    case _ => ""
  }

  println(numbersMatch)
}
