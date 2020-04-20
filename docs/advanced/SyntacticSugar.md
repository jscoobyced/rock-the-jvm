# Scala Cheat Sheet - Syntactic Sugar

Single Parameter functions
==========================
We can call a single parameter function using `{}`:
```
println {
    /// some code
    "Hello, Scala~"
}
```

Single abstract function
========================
To override a method in an abstract class having a single method, we can use a **lanbda** expression:
```
val someInstance: SingleMethodAbstractClass = (x: Type) => { // code }
```

Colon and Hashtag
=================
Last character of the `::` or `#::` (for **stream**) operator decides of the assiciativity. Therefore:
```
1 :: 2 :: 3 :: List(4, 5)
// is equivalent to 
1 :: 2 :: List(4, 5).::(3)
// and by extension
List(4, 5).::(3).::(2).::(1)
```

Backtick function notation
==========================
A method name can be enclosed in backticks then contain spaces.
```
class TeenGirl(name: String) {
    def `and then said`(gossip: String) = println(s"$name said $gossip")
    }
val lilly = new TeenGirl("Lilly")
lilly `and then said` "scala is sweet."
```

More infix types
================
A different notation for specifying types:
```
class Composite[A, B]

// val composite: Composite[Int, String] = ???
val composite: Int Composite String = ???
```

Update function
===============
In mutable collections:
```
val anArray = Array(1,2,3)
anArray(2) = 7
// is re-written to
anArray.update(2, 7)
```

Setters and getters
===================
Provide 2 specific functions to enable getter and setter:
```
class Mutable {
  private var internalMember: Int = 0
  def member = internalMember
  def member_=(value: Int): Unit = internalMember = value
}

val aMutabe = new Mutable
aMutabe.member = 7
```
