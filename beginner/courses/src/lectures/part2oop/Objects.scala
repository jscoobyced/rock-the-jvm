package lectures.part2oop

object Objects extends App {

  object Person {
    val N_EYES = 2
    var legs = 2
    def canFly: Boolean = false

    def from(mother: Person, father:Person) : Person = new Person("Bobby")

    class Person(val name: String) {

    }
  }

  println(Person.N_EYES)
  println(Person.canFly)
  println(Person.legs)
}
