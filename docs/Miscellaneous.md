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
When creating unsafe API, wrap with `Some` or return `None` instead of `null`.