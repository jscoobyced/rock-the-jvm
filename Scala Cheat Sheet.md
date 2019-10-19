# Scala Cheat Sheet

Miscellaneous
=============

Create a class that can be run: <span style="color:#a3238e;">extends App</span>

By defaults is ***everything should be immutable*** (better for // execution and multi-threading)

Trailing semi-colon is optional, unless required (i.e. more than 1 <span style="color:#faa61a;">statement</span> in a line)

Types
=====

Types can be <span style="color:#faa61a;">inferred</span>

<span style="color:#a3238e;">val</span> is <span style="color:#faa61a;">immutable</span>

<span style="color:#a3238e;">Short</span> is represented by 2 bytes

<span style="color:#a3238e;">Int</span> is represented by 4 bytes

<span style="color:#a3238e;">Long</span> is represented by 8 bytes. Long end with a <span style="color:#0066b3;">L</span>

<span style="color:#a3238e;">Float</span> ends with a <span style="color:#0066b3;">f</span>

<span style="color:#a3238e;">Double</span> is a decimal without ending letter (that would mark it as Float)

<span style="color:#a3238e;">var</span> is re-assignable

<span style="color:#a3238e;">var</span> is used for <span style="color:#faa61a;">side-effects</span>

<span style="color:#faa61a;">side-effects</span> are expressions returning a <span style="color:#a3238e;">Unit</span>

<span style="color:#faa61a;">Expression</span> is a value, it is evaluated. In Scala everything is an <span style="color:#faa61a;">expression</span>

<span style="color:#faa61a;">Instruction/Statement</span> is executed (“compute this”, “print that”…)

A <span style="color:#faa61a;">code block</span> is a piece of code in curly brackets, which value is the last <span style="color:#faa61a;">expression</span> value. <span style="color:#faa61a;">Variables</span> and <span style="color:#faa61a;">Values</span> are scoped inside the <span style="color:#faa61a;">code block</span>.

Functions
=========

<span style="color:#55308d;">def</span> to create a <span style="color:#faa61a;">function</span>, also called <span style="color:#faa61a;">auxiliary function</span>.

Parameter-less <span style="color:#faa61a;">function</span> can be called without parenthesis

When need a while loop, use recursion instead

<span style="color:#faa61a;">Return type</span> of <span style="color:#faa61a;">function</span> is not necessary, unless there is recursion. Best practice is to always write the return type.

You can have a <span style="color:#faa61a;">function</span> inside of a <span style="color:#faa61a;">function</span>.

Type inference
==============

The <span style="color:#faa61a;">compiler</span> is able to infer the <span style="color:#faa61a;">type of a value</span> or <span style="color:#faa61a;">function</span> by looking at the right hand side of the statement or the return type of a function.

You can’t force the wrong <span style="color:#faa61a;">type</span>.

The <span style="color:#faa61a;">compiler</span> cannot infer the return type of a <span style="color:#faa61a;">recursion</span>.

Recursions
==========

<span style="color:#faa61a;">Recursions</span> use a <span style="color:#faa61a;">stack</span> to keep the last <span style="color:#faa61a;">recursion result</span> if needed to compute a new value. This can lead to <span style="color:#faa61a;">stack overflow error</span> when a too large number of recursion occurs.

To prevent this, using a <span style="color:#faa61a;">tail recursion</span> or <span style="color:#faa61a;">tail recursive function</span> will re-use the last stack and store the new result. A <span style="color:#faa61a;">tail recursion</span> uses the <span style="color:#faa61a;">recursive call</span> as the last <span style="color:#faa61a;">expression</span>.

To check or force compiler to check for tail recursion, add the <span style="color:#a3238e;">@tailrec</span> annotation before the function definition.

When you need a <span style="color:#faa61a;">loop</span>, use a <span style="color:#faa61a;">tail recursion.</span>

Calling by Name or Value
========================

When an expression is passed to a function by value:\* It is computed before the call

-   The same value is used throughout the execution of the function

When an expression is passed to a function definition by name:\* The expression is passed literally

-   It is evaluated at every use within the function

The notation for calling by name is <span style="color:#a3238e;">variable\_name: =&gt; variable\_type</span>. Example:

<div style="color:#127622;">
def calledByName(x: =&gt; Long)
</div>

It can be useful for <span style="color:#faa61a;">lazy stream</span> and error management.
