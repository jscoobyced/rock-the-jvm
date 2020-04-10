package exercises.part3fp

object TuplesAndMapsExercises extends App {

  val aMap = Map("jim" -> 123, "JIM" -> 456)
  println(aMap.map(pair => pair._1.toLowerCase() -> pair._2))

  // Map[String, List[String]]
  class Network(network: List[Map[String, List[String]]] = List(Map())) {

    def add(person: String): Network ={
      val friends: List[String] = List()
      new Network(network :+ Map(person -> friends))
    }

  }

}
