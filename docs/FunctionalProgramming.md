# Scala Cheat Sheet - Functional Programming in Scala

Functions
=========

Ideally **function**s are first programming elements, which is not entirely possible due to the nature of JVM where everything is an object.

Using the `apply` method, it is possible to use a class instance as a **function**:
```
val doubler = new Function1[Int, Int] {
    override def apply(value: Int): Int = value * 2
}

println(doubler(4)) // prints 8
```

There are already 22 predefined ``Function1`` to ``Funtion22`` that accept 1 to 22 input parameter and return 1 result.

All **function**s are instances of classes derived of `Function1`...`Function22`

**Higher-order function**s receive a **function** as parameter, or return a **function** as a result:
```
val higherOrder: Funtion1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int) : Function1[Int, Int] = new Funtion1[Int, Int] {
        override def apply(y: Int): Int = x + y
    }
}
val higher5 = higherOrder(5)
val result = higher5(10)
```

A **curried function** is a **function** that returns a **function**, and allows to be called with list of parameters:
```
val result = higherOrder(5)(10)
```

Syntactic sugar for `Function2[Type1, Type2, Type3]` is `(Type1, Type2) => Type3`.

Anonymous Functions
===================

**Lambda function**s are anonymous functions that use the syntactoc sugar for `Function1` ... `Function22`:
```
val doubler = (x: Int) => x * 2
// or
val doubler: Int => Int = x => x * 2
```

If there is more than 1 parameter, the parameters must be between paranthesis: `val adder: (Int, Int) => Int = (x, y) => x + y`

If there is no parameter, use empty paranthesis as parameter: `val highFive: () => Int = () => 5`

**Lambda**s must be called with paranthesis, otherwise the value is the **function** itself.

Another notation is to use curly braces:
```
val doubler = { (x: Int) =>
    x * 2
}
```

The underscore **placeholder** is used to replace a `x => x` notation:
```
val doubler: Int => Int = x => x +1
// or
val doubler: Int => Int = _ + 1
```

With more parameters, each `_` can be used to represent each parameter in sequence:
```
val added: Int => Int = (a, b) = a + b
// or
val added: Int => Int = _ + _
```

Higher-Order Functions
======================

A **higher order function** (HOF)  is a function that takes a function as parameter or returns a function as a result.

A function with a list of parameter is a **curried function**. The type for the smaller functions must have type defined.

Map and al
==========

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
