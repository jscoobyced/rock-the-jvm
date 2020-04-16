package exercises.part2oop

import scala.language.postfixOps;

object PersonExercise extends App {
  class Person(val name: String, favoriteMovie: String, age: Int = 0) {
    def apply(): String = s"$name is $age years old and likes $favoriteMovie"
    def +(title: String): Person = new Person(s"$name ($title)", favoriteMovie, age)
    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)
    def apply(number: Int): String = s"$name watched $favoriteMovie $number times."
    def learns(topic: String): String = s"$name is learning $topic."
    def learnsScala : String = this learns "Scala"

  }

  val cedric = new Person("Cedric", "The Teletubbies", 20)
  println(cedric())
  println((cedric + "the NPC")())
  println((+cedric)())
  println(cedric(2))
  println(cedric learnsScala)
}
