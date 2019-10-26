package lectures.part2oop

object OOBasics extends App {

  val person = new Person(" Cedric", 42)
  person.greet("Daniel")
}

class Person(name: String, val age: Int) {

  def greet(name: String): Unit =
    println(s"${this.name} says: Hi, $name")

  def greet(): Unit = println(s"Hi, I am $name")
}