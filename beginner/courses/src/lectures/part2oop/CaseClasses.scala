package lectures.part2oop

object CaseClasses extends App {

  case class Person(name: String, age: Int)

  val cedric = new Person("Cedric", 25)
  println(cedric.name)
  println(cedric)
  val cedric2 = new Person("Cedric", 25)
  println(cedric == cedric2)

  val cedric3 = cedric.copy(age = 99)
  println(cedric3)


}
