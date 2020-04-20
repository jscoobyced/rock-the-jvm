package lectures.part4pm

import scala.util.Random

object PatternMatching extends App {

  val random = new Random

  val x = random.nextInt(10)

  val description = x match {
    case 1 => "The one"
    case 2 => "Double or nothing"
    case 3 => "Third time is the charm"
    case _ => "Default"
  }

  println(x)
  println(description)

  case class Person(name: String, age: Int)
  val bob = Person("Bob", 35)

  val greeting = bob match {
    case Person(n, a) if a < 40 => s"Hi, my name is $n and I am a young $a years old guy."
    case Person(n, a) => s"Hi, my name is $n and I am $a years old."
    case _ => "I don't know who I am."
  }

  println(greeting)

  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Golden Retriever")

  animal match {
    case Dog(someBreed) => println(s"Found a $someBreed")
  }
}
