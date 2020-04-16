package io.narok.scala.fs.core

import io.narok.scala.fs.files.Directory

class State(val root: Directory, val workingDirectory: Directory, val output: String, val on: Boolean) {

  def show: Unit = {
    if (!output.isEmpty) println(output)
    if(on) print(State.PROMPT)
  }

  def setMessage(message: String): State = State(root, workingDirectory, message)

  def off(): State = State(root, workingDirectory, "Goodbye!", false)
}

object State {
  val PROMPT = "$ "

  def apply(root: Directory, workingDirectory: Directory, output: String = "", on: Boolean = true): State =
    new State(root, workingDirectory, output, on)
}