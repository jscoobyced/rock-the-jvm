package lectures.part3fp

object Options extends App {

  val myFirstOption: Option[Int] = Some(3)
  val noOption: Option[Int] = None

  println(myFirstOption)
  println(noOption)

  def unsafeMethod(): String = null

  val result = Option(unsafeMethod())
  println(result)

  def backupMethod() : String = "Hello, Scala!"

  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))
  println(chainedResult)

  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("Hello, Scala!")
  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()
  println(betterChainedResult)

  println(myFirstOption.isEmpty)

  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(_ % 2 == 0))
  println(myFirstOption.flatMap(x => Option(x * 10)))

  val forComprehensionResult = for {
    m <- myFirstOption
  } yield m * 10
  println(forComprehensionResult)

}
