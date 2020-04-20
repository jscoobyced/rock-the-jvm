package lectures.part2oop

import scala.language.postfixOps;

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String) {
    def likes(movie: String): Boolean = movie == favoriteMovie
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def unary_! : String = "Whatever, dude!"
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception")

  val tom = new Person("Tom", "Titanic")

  println(mary + tom)
  println(mary.+(tom))
  println(!mary)
  println(mary.isAlive)
  println(mary isAlive)
  println(mary.apply)
  println(mary())
}
