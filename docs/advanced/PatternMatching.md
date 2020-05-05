# Scala Cheat Sheet - Advanced Pattern Matching

Infix and `Nil`
==============
Below would match if the list has only 1 element (i.e. only the head woud have a value, and the tail is `Nil`)
```
val numbers = List(1)
val description = numbers match {
    case head :: Nil => println(s"the only element is $head")
    case _ =>
}
```

Unapply, unapplySeq
===================
If a class cannot be pattern matched, use a singleton companion object with an `unapply` function that returns an `Option` deconstructing the base class:
```
object ClassName {
    def unapply(className: ClassName): Option[(string, int)] = Some((className.a, className.b))
    def unapplySeq(list: List) =
        if (list.isEmpty()) Some(List())
        else unapplySeq(list.tail).map(list.head +: _)
}
```

Return `None` when you don't want a condition to match.
`Case` classes have a built-in `unapply` function.
Use `unapplySeq` to wildcard match:
```
val result = someList match {
    case List(1, 2, _*) => s"List starts with 1 and 2"
    case _ => "something else"
}
```

To return a custom type in `unapply`, we need a type that contains two functions:
```
isEmpty; boolean
get: T
```