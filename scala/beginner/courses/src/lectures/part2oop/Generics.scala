package lectures.part2oop

object Generics extends App {
  class Animal

  class Cat extends Animal
  class Siamese extends Cat
  class Dog extends Animal

  class CovariantList[+A] {
    def add[A](value: A): CovariantList[A] = new CovariantList[A]
  }

  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  animalList.add(new Cat())
  animalList.add(new Dog()) // ???
  animalList.add(new Siamese())

  class Trainer[-A]
  var trainer: Trainer[Cat] = new Trainer[Animal]

  class Cage[A <: Animal](animal: A)
  val cage = new Cage(new Dog)


  class MyList[+A] {
    def add[B >: A](element: B): MyList[B] = ???
  }
}
