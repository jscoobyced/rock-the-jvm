# Scala Cheat Sheet - Miscellaneous

Options
=======

`Option` is a wrapper for a value that might not exist. It is an attempt to address the existance of `null` which creates errors or lengthy code to avoid them.

`Some` is a subclass of `Option` and wraps a concrete value.

`None` is also a subclass of `Option` and is a **singleton** for the absence of value.

`Option` is used to wrap the result of something that might be `null`. Do not use `Some` to wrap suche a call.
```
val badResult = Some(someNullMethod())
val goodResult = Option(someNullMethod())
```
When using unsafe API, wrap with `Option` and eventually fallback with a `orElse` to chain a safe result:
```
val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))
```
When creating unsafe API, wrap the result with `Some` or return `None` instead of `null`.

Exceptions
==========

A better way to handle exception is to use a `Try` block that is subclassed by `Failure` and `Success`.

We call a potentially unsafe method in the `apply` method of the `Try` construct:
```
val someResult = Try(unsafeMethod())
```

When using unsafe API, wrap with `Try` and eventually fallback with a `orElse` to chain a safe result:
```
val fallbackTry = Try(unsafeMethod()) orElse Try(backupMethod())
```
When creating unsafe API, return a `Try[type]` wich is either a `Success` or `Failure` instance.

Pattern matching
================

The `match` keywords can be used to match a value and do something about it.

The default pattern matching symbol `_` is the **wildcard** pattern.

We can use **pattern matching** to decompose an object:
```
val result = someInstance match {
    case Person(name, age) => s"My name is $name and I am $age years old."
    case _ => "I don't know what I am."
}
```

`Case`s are matched in order. If nothing matches (i.e. no wildcard) it will return a `MatchError`.

The return type of a `match` is the unified type of all types in all `cases`. If there is no unification, the type is `Any`.

`Case` classes can be used for pattern matching.