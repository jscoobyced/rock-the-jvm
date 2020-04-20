package lectures.part1basics

object StringOps extends App {

  val str: String = "Hello, I am learning Scala."

  println(str.charAt(2))

  val numberString = "45"
  val aNumber = numberString.toInt

  println('a' +: numberString :+ 'z')

  val name = "Cedric"
  val age = 42

  val greeting = s"Hello, my name is $name and I am $age years old."
  val anotherGreeting = s"Hello, I am turning ${age + 1} years old."
  println(anotherGreeting)

  val speed = 1.2f
  val myth = f"$name can eat $speed%2.2f burgers per minute."
  println(myth)
}