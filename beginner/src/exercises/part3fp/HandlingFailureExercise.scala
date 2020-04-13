package exercises.part3fp

import scala.util.{Random, Try}

object HandlingFailureExercise extends App {

  val hostname = "localhost"
  val port = "8080"
  val url = "/index.html"

  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted.")
    }

    def getSafe(url: String) : Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime() / 6235)

    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Connection failed.")
    }

    def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }

  Try(HttpService.getConnection(hostname, port))
    .map(connection => Try(connection.get(url))
      .map(page => renderHTML(page))
      orElse Try(println("Could not get the page."))
    ) orElse Try(println("Could not get a connection."))

  val possibleConnection = HttpService.getSafeConnection(hostname, port)
  val possibleHTML = possibleConnection.flatMap(connection => connection.getSafe(url))
  possibleHTML.foreach(renderHTML)

  HttpService.getSafeConnection(hostname, port)
    .flatMap(connection => connection.getSafe(url))
    .foreach(renderHTML)

  for {
    connection <- HttpService.getSafeConnection(hostname, port)
    page <- connection.getSafe(url)
  } renderHTML(page)
}
