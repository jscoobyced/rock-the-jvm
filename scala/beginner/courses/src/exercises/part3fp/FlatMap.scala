package exercises.part3fp

abstract class Maybe[+A] {
  def map[B](transformer: A => B): Maybe[B]

  def flatMap[B](transformer: A => Maybe[B]): Maybe[B]

  def filter(predicate: A => Boolean): Maybe[A]

}

case object MaybeNot extends Maybe[Nothing] {
  override def map[A](transformer: Nothing => A): Maybe[A] = MaybeNot

  override def flatMap[A](transformer: Nothing => Maybe[A]): Maybe[A] = MaybeNot

  override def filter(predicate: Nothing => Boolean): Maybe[Nothing] = MaybeNot
}

case class Just[+A](h: A) extends Maybe[A] {
  override def map[B](transformer: A => B): Maybe[B] = Just(transformer(h))

  override def flatMap[B](transformer: A => Maybe[B]): Maybe[B] = transformer(h)

  override def filter(predicate: A => Boolean): Maybe[A] = {
    if (predicate(h)) this
    MaybeNot
  }
}

object MaybeTest extends App {

  val just1: Just[Int] = Just(1)
  val justHello: Just[String] = Just("hello")
  println(just1.map(_ * 2))
  println(just1.flatMap(x => Just(x % 2 == 0)))
  println(just1.filter(_ % 2 == 0))

  val combination = for {
    n <- just1
    i <- justHello
  } yield n + i
  println(combination)

}
