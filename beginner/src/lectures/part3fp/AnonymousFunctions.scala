package lectures.part3fp

object AnonymousFunctions extends App {

  val doubler: Int => Int = v1 => v1 * 2

  val niceIncrementor: Int => Int = _ + 1

  val inception = (x: Int) => (y: Int) => x + y
  println(inception(3)(10))
}
