package exercises.part2oop

abstract class MyList[+A] {

  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def add[B >: A](value: B): MyList[B]

  def printElements: String

  def map[B](transformer: A => B): MyList[B]

  def flatMap[B](transformer: A => MyList[B]): MyList[B]

  def filter(predicate: A => Boolean): MyList[A]

  def ++[B >: A](list: MyList[B]): MyList[B]


  override def toString(): String = "[" + printElements + "]"
}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException

  def tail: MyList[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](value: B): MyList[B] = new Cons(value, Empty)

  def map[B](transformer: Nothing => B): MyList[B] = Empty

  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty

  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list


  def printElements: String = ""
}


case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h

  def tail: MyList[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](value: B): MyList[B] = new Cons(value, this)

  def map[B](transformer: A => B): MyList[B] = {
    new Cons(transformer(h), t.map(transformer))
  }

  def filter(predicate: A => Boolean): MyList[A] = {
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter((predicate))
  }

  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, tail ++ list)

  def flatMap[B](transformer: A => MyList[B]): MyList[B] = {
    transformer(h) ++ t.flatMap(transformer)
  }

  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements
}

object ListTestGeneric extends App {
  val listOfInteger = new Cons[Int](1, new Cons(2, new Cons(3, Empty)))
  val clonedListOfInteger = new Cons[Int](1, new Cons(2, new Cons(3, Empty)))
  println(listOfInteger.tail.head)
  println(listOfInteger.add(4).head)
  println(listOfInteger.toString)
  val listOfString = new Cons[String]("I", new Cons("can", new Cons("do", new Cons("scala.", Empty))))
  println(listOfString.toString())
  println(listOfInteger == clonedListOfInteger)
}

object NewListTest extends App {
  val listOfString = new Cons[String]("01", Cons("002", Cons("3", Cons("04", Empty))))
  println(listOfString.toString())
  val mappedToInt = listOfString.map((_.toInt))
  println(mappedToInt.toString())
  println(mappedToInt.filter((_ % 2 == 0)))
  println(mappedToInt.flatMap((value => Cons(value, Cons(value + 1, Empty)))))

}