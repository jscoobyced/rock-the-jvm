package lectures.part1as

import scala.util.Try

object DarkSugar extends App {

  def singleArgMethod(arg: Int): String = s"$arg whatever"

  val description = singleArgMethod {
    // some code
    42
  }

  val aTryInstance = Try {
    // ...
  }

  List(1,2,3,4).map { x =>
    x + 1
  }

  trait Action {
    def act(x: Int): Int
  }

  val anInstance: Action = new Action {
    override def act(x: Int): Int = x + 1
  }

  val aFunkyInstance: Action = (x: Int) => x + 1

  val aThread = new Thread(new Runnable {
    override def run(): Unit = println("nice")
  })

  val aBetterThread = new Thread(() => println("nicer"))

  abstract class AnAbstractType {
    def implemented: Int = 23
    def f(a: Int): Unit
  }

  val anAbstractInstance: AnAbstractType = (a: Int) => println("sweet")

  val prependedList = 2 :: List(3, 4)

  class TeenGirl(name: String) {
    def `and then said`(gossip: String) = println(s"$name said $gossip")
  }

  val lilly = new TeenGirl("Lilly")
  lilly `and then said` "scala is sweet."

  class Composite[A, B]

  // val composite: Composite[Int, String] = ???
  val composite: Int Composite String = ???

  val anArray = Array(1,2,3)
  anArray(2) = 7

  class Mutable {
    private var internalMember: Int = 0

    def member = internalMember
    def member_=(value: Int): Unit = internalMember = value
  }

  val aMutabe = new Mutable
  aMutabe.member = 7
}
