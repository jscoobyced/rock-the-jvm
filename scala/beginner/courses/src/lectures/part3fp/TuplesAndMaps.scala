package lectures.part3fp

object TuplesAndMaps extends App {

  val aTuple = (2, "Hello, Scala")

  println(aTuple._1)
  println(aTuple.copy(_2 = "Another value"))
  println(aTuple.swap)

  val aMap: Map[String, Int] = Map()
  val phoneBook = Map(("Jim", 5555), ("John", 5556)).withDefaultValue(-1)

  println(phoneBook)

  println(phoneBook.contains("Jim"))
  println(phoneBook("Jim"))
  println(phoneBook("Mary"))

  val newPairing = "Jane" -> 998
  val newPhoneBook = phoneBook + newPairing
  println(newPhoneBook)

  println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2))

  println(phoneBook.filterKeys(x => x.startsWith("Jo")).toMap)

  println(phoneBook.mapValues(number => "+1 " + number).toMap)

  val names = List("John", "Jane", "Mike", "Mary", "Bob", "Alice")
  println(names.groupBy(name => name.charAt(0)))


}
