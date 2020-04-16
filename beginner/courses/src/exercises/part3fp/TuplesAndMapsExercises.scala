package exercises.part3fp

object TuplesAndMapsExercises extends App {

  val aMap = Map("jim" -> 123, "JIM" -> 456)
  println(aMap.map(pair => pair._1.toLowerCase() -> pair._2))

  case class Network(network: Map[String, Set[String]] = Map()) {

    def add(person: String): Network = {
      val friends: Set[String] = Set()
      Network(network + (person -> friends))
    }

    def remove(person: String): Network = {
      def removeAux(friends: Set[String], networkAccumulator: Map[String, Set[String]]): Network =
        if (friends.isEmpty) Network(networkAccumulator)
        else removeAux(friends.tail, Network(networkAccumulator).unfriend(person, friends.head).network)

      val unfriended = removeAux(network(person), network)
      Network(unfriended.network - person)
    }

    def friend(person1: String, person2: String): Network = {
      val friends1 = network(person1)
      val friends2 = network(person2)
      Network(network + (person1 -> (friends1 + person2)) + (person2 -> (friends2 + person1)))
    }

    def unfriend(person1: String, person2: String): Network = {
      val friends1 = network(person1)
      val friends2 = network(person2)
      Network(network + (person1 -> (friends1 - person2)) + (person2 -> (friends2 - person1)))
    }

    def numberOfFriends(person: String): Int =
      if (!network.contains(person)) 0
      else network(person).size

    def mostPopular(): String =
      network.maxBy(_._2.size)._1

    def losersCount(): Int = network.count(_._2.isEmpty)

    def socialConnection(person1: String, person2: String): Boolean = {
      def breadFirstSearch(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]) : Boolean = {
        if (discoveredPeople.isEmpty) false
        else {
          val person = discoveredPeople.head
          if (person == target) true
          else if (consideredPeople.contains(person)) breadFirstSearch(target, consideredPeople, discoveredPeople.tail)
          else breadFirstSearch(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
        }
      }

      breadFirstSearch(person2, Set(), network(person1) + person1)
    }

  }

  val myNetwork = Network(Map())
  println(myNetwork)
  val myNewNetwork = myNetwork.add("Jim").add("John").add("Jane").add("Mary")
  println(myNewNetwork)

  println(myNewNetwork.remove("John"))
  val myBiggerNetwork = myNewNetwork.friend("Jim", "Mary").friend("Jim", "Jane")

  println(myBiggerNetwork.unfriend("Jim", "Mary"))
  println(myBiggerNetwork)

  println(myBiggerNetwork.numberOfFriends("Jim"))
  println(myBiggerNetwork.mostPopular())
  println(myBiggerNetwork.losersCount())
  println(myBiggerNetwork.socialConnection("Jane", "Mary"))


}
