package exercises.part2oop

import scala.runtime.Nothing$

abstract class MyList {

  def head : Int
  def tail : MyList
  def isEmpty : Boolean
  def add(value: Int) : MyList
  def printElements: String

  override def toString(): String = "[" + printElements + "]"
}

object Empty extends MyList {
  def head : Int = throw new NoSuchElementException
  def tail : MyList = throw new NoSuchElementException
  def isEmpty : Boolean = true
  def add(value: Int) : MyList = new MyIntList(value, Empty)

  def printElements: String = ""
}

class MyIntList(h: Int, t: MyList) extends MyList {
  def head : Int = h
  def tail : MyList = t
  def isEmpty : Boolean = false
  def add(value: Int) : MyList = new MyIntList(value, this)
  def printElements: String =
    if(t.isEmpty) "" + h
    else h + " " + t.printElements
}

object ListTest extends App {
  val list = new MyIntList(1, new MyIntList(2, new MyIntList(3, Empty)))
  println(list.tail.head)
  println(list.add(4).head)
  println(list.toString)
}