package lectures.part3fp

object MapFlatmapFilterFor extends App {

  val list = List(1, 2, 3)
  println(list)
  println(list.map(_ + 1))
  println(list.filter(_ >= 2))
  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  // print all combinations of the lists
  val numbers = List(1, 2, 3, 4)
  val characters = List('a', 'b', 'c', 'd')
  val colors = List("white", "black")

  println(numbers.flatMap(n => characters.flatMap(c => colors.map(co => s"$c$n-$co"))))

  // for-comprehension
  val forCombination = for {
    n <- numbers
    c <- characters
    co <- colors
  } yield s"$c$n-$co"

  println(forCombination)


}
