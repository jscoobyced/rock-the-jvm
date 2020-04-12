# Scala Cheat Sheet - Collections in Scala

Collections
===========

There is a built-in `List` that provides all the `map`, `filter`, `sort`... functionalities

**for-comprehension** is a notation that makes it more readable when a series of `map` and `flatMap` are cascaded:
```
  val numbers = List(1, 2, 3, 4)
  val characters = List('a', 'b', 'c', 'd')
  val colors = List("white", "black")

  val hardToRead = numbers.flatMap(n => characters.flatMap(c => colors.map(co => s"$c$n-$co"))))

  // is equivalent to the for-comprehension
  val forCombination = for {
    n <- numbers
    c <- characters
    co <- colors
  } yield s"$c$n-$co"

```

It is possible to **guard** any of the list by adding an `if` statement on the same line. It is translated to a `filter` function:
```
  val forCombination = for {
    n <- numbers if n % 2 == 0
    c <- characters
    co <- colors
  } yield s"$c$n-$co"

```
A **for-loop** can be written:
```
for {
    n <- numbers
} println(n)
```
A **map** syntax-overload is:
```
list.map { x =>
    x * 2
}
```

Immutable Collections
=====================

`Traversable` is the base `trait` of all **collections**.

`Iterable` extends `Traversable`

`Seq` extends `Iterable`

`Set`s cannot include duplicates

The `List` two sub-types are `Nil` **object** and `::` **class**. Using a value followed by `::` then a `List` is equivalent to calling the `apply` method on a `List`:
```
val prepended = 42 :: aList
val prependedToo = ::.apply(42, aList)
```
Difference between `5 :: List(1,2,3)` and `5 +: List(1,2,3)` ?
```
val anotherPrepended = 42 +: aList :+ 56
val anotherPrependedToo = aList.prepended(42).appended(56)
```

`List` have constant execution time for `head`, `tail` and `isEmpty`  
`List` have linear execution time for `length`, `reverse`...  

`Vector` have effectively constant execution time  read and write: O(log32(n))

`Tuple`s are finite ordered kind-of `List`. It can be initialized with full notation or short notation:
```
val aTuple = new Tuple2(2, "Hello, Scala!")
// or
val anotherTuple = Tuple2(2, "Hello, Scala!")
// or even 
val yetAnotherTuple = (2, "Hello, Scala!")
```

There can be up to 22 elements in a `Tuple`

`Map`s are used to associate **key**s to **value**s.

We can use the syntactic sugar `a -> b` to link a **key** to a **value**.

Accessing an element of a `Map` with a non-existant `key` will result in a `NoSuchElementException`. It is possible to provide a default value:
```
val aMap = Map("John" -> 123, "jim" -> 456).withDefaultValue(-1)
```
`Map` being immutable, to add a `tuple`:
```
val newMap = oldMap + (key,  value)
```
Using `groupBy` on a `List` creates a `map`.
