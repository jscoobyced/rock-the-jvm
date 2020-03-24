# Scala Cheat Sheet - Object Oriented Programming
Object Oriented
===============

Create a class definition: `class MyClass() { ... }`
**Class parameters** are not fields. Add the `val` keyword to make a **class parameter** a class field:
```
class Person(name: String, val age: Int)
```

Class implementation is delimited by curly brackets.  
Class can have multiple constructors, using the `this` function name:
```
def this(name: String) = this(name, 0)
```
It is possible to use **default argument** with primary constructor.

Method Notations
================

**Methods** that have a single parameter can be called using **infix notation** or **operator notation**. Example:
`movieList.contains("Titanic")`  
can be written  
`movieList  contains "Titanic"`

The name **operator notation** refers to the way we use mathematical operators `+, -, *, /`. In scala, `1.+(2)` and `1 + 2` are equivalent.

**Unary** operator supported: `-, +, ~ and !`:
```
class MyClass {
  def unary_! : String = s"Whatever"
}

var myClass = new MyClass
println(!myClass) // will print "Whatever"
```
This is also known as **prefix notation**

There is **postfix notation** that can be used with method not having argument and using a space instead of a `.`. But it is rarely used as it can be confusing for other devs to read the code.

Creating an `apply()` (without parameter) allows using the name of the value/variable with parenthesis `()` and it will call this `apply()` method.
```
class MyClass {
  def apply(): String = s"Whatever"
}

var myClass = new MyClass
println(myClass()) // will print "Whatever"
```

Objects
=======

*Scala does not have class level functionality* i.e. `static` doesn't exist.

``object`` is a singleton instance

``class`` and ``object`` in the same scope with same name are **companions**

An **application** is an ``object`` with a 
```
def main(args: Array[String]): Unit
```

Scala is single ``class`` inheritance

``private`` modifier makes the element available in the class itself only

``protected`` modifier makes the element available in the class itself and subclass(es)

Subclass calls the super **constructor** before it's own

Use the ``override`` keyword before a `val`/`var`/`def` to override them (as long as they are not `private`)

Overriding in the constructor:
```
class Parent {
  val something: Type = "stuff"
}

class Child(override val something: Type) extends Parent
val child = new Child(something)
```

**Type substitution** or **polymorphism** allows a child class that extends a parent to be assigned to a typed value of the parent class

**overloading** can be used to create several `def` having the same name but different number of arguments

The **super** keyword allows to access the parent version of a overriden `var`/`val`/`def`

You can add the `final` modifier to prevent overriding, including on the a member or `class`

A softer version of `final` is `sealed`, which allows to `extends` within the same file

`abstract` class cannot be implemented, must be extended

`override` keyword is not mandatory in subclass of `abstract` class

A `trait` describes a behaviour, it can contain implemented members.
Traits can't have constructor parameter.
A class can inherit multiple `trait`s

`Any` is the top-most class hierarchy  
`AnyRef` is the parent of all objects (i.e. equivalent to java.lang.Object)  
`AnyVal` is the parent of all primitives  
`Null` extends all `AnyRef`  
`Nothing` extends `AnyVal` and `Null`


Generics
========

Define the **generic** type using square brackets `[A]` or `[A, B]`

`Trait`s and methods can also be **generic**

`Covariance` in scala is noted with a `[+A]` **generic** type. This allows to assign a subclass type to a parent class type. Example:
```
class Creature
class Animal extends Creature
class Cat extends Animal
class Dog extends Animal
class CovariantList[+A]
val catList: CovariantList[Animal] = new CovariantList[Cat]
catList.add(new Cat())
```

`Invariance` is simple `[A]` **generic** type. This does not allow to substitue a subclass

`Contravariance` is noted by `[-A]` allows to assign a parent class like:
```
class Trainer[-A]
var trainer: Trainer[Cat] = new Trainer[Animal]
```

`Bounded types` allows the super-class or sub-class to be substitued
Example for sub-class: only sub-class of `Animal` is allowed.
```
class Cage[A <: Animal](animal: A)
val cage = new Cage(new Dog)
```

Example for super-class: only a parent class of `Animal` is allowed.
```
class Cage[A >: Animal](creature: A)
val cage = new Cage(new Creature)
```

Anonymous classes
=================

A **annonymous class** is a compilation time created class of a inline implemented abstract class, class or trait.

It is possible to re-implement/override a method inline, as long as all constructor arguments are provided. Example:
```
class Person(name: String) {
  def sayHi: Unit = println(s"Hi, my name is $name.")
}

val jim = new Person("Jim") {
  override def sayHi: Unit = println(s"Hi, my name is Jim. How are you?")
}
```

You must implement all abstract fields/methods in the anonymous class.

Case classes
============

**Case** class has class parameters as fields

Syntax is
```
case class Person(name: String, age: Int)
```

`toString` override is more friendly than the default implementation.

`equals` and `hashCode` are implemented

`copy` method allows to copy the case class, optionnaly overriding fields values
```
val john = new Person("john", 45)
val jim = john.copy(age = 25)
```

There is companion objects available. I.e. the `apply` methods allows to do `val jack = Person("jack", 30)`. The `new` keyword is not necessary.

**Case** classes are serializable out of the box. They also have exctractor patterns, so they can be used in pattern matching.

**Case** objects are similar to **case** classes except there is no companion object (they are the companion object themselves)

Exceptions
==========

**Exception**s in Scala are similar to Java Exceptions:  
- **Exception** and **Error** extend from **Throwable**
- When an **exception** is thrown, it crashes the JVM
- When an **exception** is thrown, it usually prints the stacktrace on the system error stream
- `try`/`catch`/`finally` blocks

**Exception**s are JVM concept, not Scala specific.

**Exception**s are thrown when something is wrong with the program

**Error**s are thrown when something is wrong with the system

`finally` doesn't influence the return type of the block. It should be used only for side-effects (i.e. logging)

Packages
========

A **Package** is a groups of definition under the same name.

Defined by a `package` keyword followed by a dot-separated list of **package** levels.

It is possible to access a member from another package either by **import**ing it or using the fully qualified name:
- `import foo.bar.MyClass`
- `val myClass = new foo.bar.MyClass`

**Package**s are in a hierarchy. They follow (99% of the time) a directory structure.

**Package** can contain a unique **package object** named `package.scala`. That allows to access a member of this **package object** in any class in the package.

To import multiple classes from a **package**, there are a few options:
- `import foo.bar.{Object1, Object2}`
- `import foo.bar._`
- `import foo.bar.{Object1 => Tada}`: this is called **aliasing**

The first option is preferred as more readable to others.

When more than one class with a given name are imported, the compiler will consider the first imported class in the file unless the usage is either aliased or fully qualified.

As in Java, there are default imports that are not required:
- `java.lang`: String, Object...
- `scala`: Int, Nothing...
- `scala.Predef`: println, ???...