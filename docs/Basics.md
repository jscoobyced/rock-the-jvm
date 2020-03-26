# Scala Cheat Sheet - Basics and Fundamentals

Miscellaneous
=============

By defaults is ***everything should be immutable*** (better for // execution and multi-threading)

Trailing semi-colon is optional, unless required (i.e. more than 1 **statement** in a line)

Types
=====

Types can be **inferred**

*val* is **immutable**

*Short* is represented by 2 bytes

*Int* is represented by 4 bytes

*Long* is represented by 8 bytes. Long end with a `L`

*Float* ends with a `f`

*Double* is a decimal without ending letter (that would mark it as Float)

*var* is re-assignable

*var* is used for **side-effects**

**side-effects** are expressions returning a *Unit*

**Expression** is a value, it is evaluated. In Scala everything is an **expression**

**Instruction/Statement** is executed (“compute this”, “print that”…)

A **code block** is a piece of code in curly brackets, which value is the last **expression** value. **Variables** and **Values** are scoped inside the **code block**.

Functions
=========

Use *def* to create a **function**, also called **auxiliary function**.

Parameter-less **function** can be called without parenthesis

When need a while loop, use recursion instead

**Return type** of **function** is not necessary, unless there is recursion. Best practice is to always write the return type.

You can have a **function** inside of a **function**.

Type inference
==============

The **compiler** is able to infer the **type of a value** or **function** by looking at the right hand side of the statement or the return type of a function.

You can’t force the wrong **type**.

The **compiler** cannot infer the return type of a **recursion**.

Recursions
==========

**Recursions** use a **stack** to keep the last **recursion result** if needed to compute a new value. This can lead to **stack overflow error** when a too large number of recursion occurs.

To prevent this, using a **tail recursion** or **tail recursive function** will re-use the last stack and store the new result. A **tail recursion** uses the **recursive call** as the last **expression**.

To check or force compiler to check for tail recursion, add the *@tailrec* annotation before the function definition.

When you need a **loop**, use a **tail recursion.**

Calling by Name or Value
========================

When an expression is passed to a function by value:
* It is computed before the call
* The same value is used throughout the execution of the function

When an expression is passed to a function definition by name:
* The expression is passed literally
* It is evaluated at every use within the function

The notation for calling by name is *variable_name: => variable_type*. Example:

```
def calledByName(x: => Long)
```

It can be useful for **lazy stream** and error management.

Default and Named Arguments
===========================

**Default argument** is set with `= xxx` after the type declaration.  
You must pass every leading arguments.
Only tail arguments (or all of them) can have a default value.  
You can pass a specific argument value by naming the argument: `myFunction(param = 800)`  
You can use named parameter in any order.

String Operations
=================

**S-interpolators**: using `$` variables in a `s"string with $parameters"` form.  
**F-interpolators**: similar to the **S-interpolator** but with `printf` supported format (using `$param%format`):
```
val speed = 1.2f
println(f"The speed is $speed%2.2f.")
```
Type is checked and doesn't allow wrong type format.  

**raw-interpolators**: similar to the **S-interpolator** but can print the raw value:
```
println(raw"This \n is not a new line.")
```
Text from a **value** will not render raw text.
