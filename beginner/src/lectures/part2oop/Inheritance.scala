package lectures.part2oop

object InheritanceAndTraits  extends App {


  class Animal {
    val creatureType = "wild"
    def eat = println("nomnomnom")
  }

  class Cat extends Animal

  val cat = new Cat
  cat.eat

  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)

  class Dog extends Animal {
    override val creatureType: String = "domestic"
    override def eat: Unit = println("crunch crunch")
  }

  val dog = new Dog
  dog.eat
  println(dog.creatureType)
}
