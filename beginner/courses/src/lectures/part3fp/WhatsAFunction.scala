package lectures.part3fp

object WhatsAFunction extends App {

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(5))

  val stringToIntConverter = new Function1[String, Int] {
    override def apply(v1: String): Int = v1.toInt
  }

  println(stringToIntConverter("3") + 4)

  val adder: (Int, Int) => Int = (v1: Int, v2: Int) => v1 + v2

  println(adder(5, 10))

  val concater: (String, String) => String = (string1: String, string2: String) => s"$string1 $string2"

  println(concater("Hello", "World"))

  val inception: Int => (Int => Int) = (value1: Int) => ((value2: Int) => value1 + value2)

  println(inception(2)(5))
}

trait MyFunction[A, B] {
  def apply(element: A): B
}