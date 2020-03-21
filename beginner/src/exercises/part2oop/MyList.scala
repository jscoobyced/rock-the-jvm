package exercises.part2oop

abstract class MyList[+A] {

  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def add[B >: A](value: B): MyList[B]

  def printElements: String

  def map[B](transformer: MyTransformer[A, B]): MyList[B]

  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]

  def filter(predicate: MyPredicate[A]): MyList[A]

  def ++[B >: A](list: MyList[B]): MyList[B]


  override def toString(): String = "[" + printElements + "]"
}

object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException

  def tail: MyList[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](value: B): MyList[B] = new Cons(value, Empty)

  def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty

  def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty

  def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list


  def printElements: String = ""
}


class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h

  def tail: MyList[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](value: B): MyList[B] = new Cons(value, this)

  def map[B](transformer: MyTransformer[A, B]): MyList[B] = {
    new Cons(transformer.transform(h), t.map(transformer))
  }

  def filter(predicate: MyPredicate[A]): MyList[A] = {
    if (predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter((predicate))
  }

  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, tail ++ list)

  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] = {
    transformer.transform(h) ++ t.flatMap(transformer)
  }

  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements
}

object ListTestGeneric extends App {
  val listOfInteger = new Cons[Int](1, new Cons(2, new Cons(3, Empty)))
  println(listOfInteger.tail.head)
  println(listOfInteger.add(4).head)
  println(listOfInteger.toString)
  val listOfString = new Cons[String]("I", new Cons("can", new Cons("do", new Cons("scala.", Empty))))
  println(listOfString.toString())
}

trait MyPredicate[-T] {
  def test(value: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(value: A): B
}

class EvenPredicate extends MyPredicate[Int] {
  override def test(value: Int): Boolean = (value % 2) == 0
}

class StringToIntegerTransformer extends MyTransformer[String, Int] {
  override def transform(value: String): Int = value.toInt
}

class PlusOneTransformer extends MyTransformer[Int, MyList[Int]] {
  override def transform(value: Int): MyList[Int] = new Cons(value, new Cons(value + 1, Empty))
}

object NewListTest extends App {
  val listOfString = new Cons[String]("01", new Cons("002", new Cons("3", new Cons("04", Empty))))
  println(listOfString.toString())
  val mappedToInt = listOfString.map(new StringToIntegerTransformer())
  println(mappedToInt.toString())
  println(mappedToInt.filter(new EvenPredicate()))
  println(mappedToInt.flatMap(new PlusOneTransformer()))

}