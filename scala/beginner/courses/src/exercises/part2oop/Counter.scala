package exercises.part2oop

class Counter(val count : Int = 0) {
  def increment: Counter = {
    println("Incrementing...")
    new Counter(count + 1)
  }

  def decrement: Counter = {
    println("Decrementing")
    new Counter(count - 1)
  }

  def increment(value: Int): Counter = {
    if(value <= 0) this
    else increment.increment(value - 1)
  }

  def decrement(value: Int): Counter = {
    if(value <= 0) this
    else decrement.decrement(value - 1)
  }

  def print = println(count)
}
