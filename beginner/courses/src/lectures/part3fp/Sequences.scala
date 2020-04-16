package lectures.part3fp

import scala.util.Random

object Sequences extends App {

  val aSequence = Seq(6, 1, 2, 5, 3, 4)
  println(aSequence)

  println(aSequence.reverse)

  println(aSequence.apply(2))

  println(aSequence ++ Seq(5, 6, 7))

  println(aSequence.sorted)

  val aRange: Seq[Int] = 1 to 10 // 1 until 10

  // aRange.foreach(println)

  val aList = List(1, 2, 3)
  val prepended = 42 :: aList
  val prependedToo = ::.apply(42, aList)
  println(prepended)
  println(prependedToo)

  val anotherPrepended = 42 +: aList :+ 56
  val anotherPrependedToo = aList.prepended(42).appended(56)
  println(anotherPrepended)
  println(anotherPrependedToo)

  val apple5 = List.fill(5)("apple")
  println(apple5)

  println(aList.mkString(", "))

  val numbers = Array(1, 2, 3, 4)
  val threeElements = Array.ofDim[Int](3)
  threeElements.foreach(println)
  println(threeElements)

  numbers(2) = 0;
  println(numbers.mkString(" "))

  val numberSeq: Seq[Int] = numbers
  println(numberSeq)

  val vector:Vector[Int] = Vector(1,2,3)
  println(vector)

  val maxRuns = 1000
  val maxCapacity = 1000000
  def getWriteTime(collection: Seq[Int]): Double = {
    val random = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(random.nextInt(maxCapacity), 0)
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  println(getWriteTime(numbersList))
  println(getWriteTime(numbersVector))
}
