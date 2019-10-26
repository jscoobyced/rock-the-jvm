package exercises.part2oop

class Writer(firstName: String, surname: String, val year: Int) {
  def fullName() = s"$firstName $surname"
}
