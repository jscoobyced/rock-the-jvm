package exercises.part3fp

import scala.util.Random

object OptionExercise extends App {

  val config: Map[String, String] = Map(
    "host" -> "127.0.0.1",
    "port" -> "8080"
  )

  class Connection {
    def connect = "connected"
  }

  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  val host = config.get("host")
  val port = config.get("port")
  val connection = host.flatMap(h => port.flatMap(p => Connection(h, p)))

    val connectionStatus = connection.map(c => c.connect)
    // println(connectionStatus)
    // connectionStatus.foreach(println)


  // or
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port)))
    .map(connection => connection.connect)
    .foreach(println)

  // or
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
  forConnectionStatus.foreach(println)
}
