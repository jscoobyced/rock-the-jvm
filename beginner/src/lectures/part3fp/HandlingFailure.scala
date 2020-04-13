package lectures.part3fp

import scala.util.{Failure, Success, Try}

object HandlingFailure extends App {

  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("Failure"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("No string.")

  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  val anotherPotentialFailure = Try {
    unsafeMethod()
  }

  println(potentialFailure.isSuccess)

  def backupMethod(): String = "A default value"

  val fallbackTry = Try(unsafeMethod()) orElse Try(backupMethod())
  println(fallbackTry)

  def aBetterUnsafeMethod(): Try[String] = Failure(new RuntimeException())
  def aBetterBackupMethod(): Try[String] = Success("A valid string")

  val aBetterFallback = aBetterUnsafeMethod() orElse aBetterBackupMethod()
  println(aBetterFallback)

  println(aSuccess.map(_ * 2))
  println(aFailure.map(println(_)))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 200))
}
