package exercises.part3fp

import java.security.InvalidParameterException

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

  def foreach(x: A => Unit): Unit

  def sort(compare: (A, A) => Int): MyList[A]

  def zipWith[B, C](list: MyList[B], zip:(A, B) => C): MyList[C]

  def fold[B](start: B)(operator: (B, A) => B): B

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

  def foreach(x: Nothing => Unit): Unit = ()

  def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = Empty

  def zipWith[B, C](list: MyList[B], zip:(Nothing, B) => C): MyList[C] = {
    if(!list.isEmpty) throw new InvalidParameterException()
    Empty
  }

  def fold[B](start: B)(operator: (B, Nothing) => B): B = start


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
    if (predicate(h)) Cons(h, t.filter(predicate))
    else t.filter((predicate))
  }

  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, tail ++ list)

  def flatMap[B](transformer: A => MyList[B]): MyList[B] = {
    transformer(h) ++ t.flatMap(transformer)
  }

  def foreach(x: A => Unit): Unit = {
    x(h)
    t.foreach(x)
  }

  def sort(compare: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] =
      if (sortedList.isEmpty) Cons(x, Empty)
      else if( compare(x, sortedList.head) <= 0) Cons(x, sortedList)
      else Cons(sortedList.head, insert(x, sortedList.tail))
    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  def zipWith[B, C](list: MyList[B], zip:(A, B) => C): MyList[C] = {
    if(list.isEmpty) throw new InvalidParameterException()
    else Cons(zip(h, list.head), tail.zipWith(list.tail, zip))
  }

  def fold[B](start: B)(operator: (B, A) => B): B =
    t.fold(operator(start, h))(operator)



  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements
}

object TestList extends App {
  val listOne = Cons(3, Cons(6, Cons(9, Empty)))
  listOne.foreach(println)
  println(Cons(9, Cons(6, Cons(12, Cons(2, Empty)))).sort((x, y) => y - x))
  val listTwo = Cons(6, Cons(12, Cons(2, Empty)))
  println(listOne.zipWith[Int, String](listTwo, "[" + _ + ";" + _ + "]"))

  println(listOne.fold(0)(_ + _))

  val combination = for {
    n <- listOne
    i <- listTwo
  } yield n + i
  println(combination)
}
