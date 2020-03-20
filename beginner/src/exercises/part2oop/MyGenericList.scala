package exercises.part2oop

abstract class MyGenericList[A] {

  def head : A
  def tail : MyList[A]
  def isEmpty : Boolean
  def add(value: A) : MyList
  def printElements: String

  override def toString(): String = "[" + printElements + "]"
}

