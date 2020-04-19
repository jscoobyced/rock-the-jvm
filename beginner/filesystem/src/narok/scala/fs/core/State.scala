package narok.scala.fs.core

import narok.scala.fs.files.Directory

class State(val root: Directory, val workingDirectory: Directory, val output: String) {

  def show: State = {
    if (!output.isEmpty) println(output)
    print(State.PROMPT)
    this
  }

  def setMessage(message: String): State = State(root, workingDirectory, message)

  def off(): State = State(root, workingDirectory, "Goodbye!")
}

object State {
  val PROMPT = "$ "

  def apply(root: Directory, workingDirectory: Directory, output: String = ""): State =
    new State(root, workingDirectory, output)
}