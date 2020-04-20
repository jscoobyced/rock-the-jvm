package narok.scala.fs.core

import narok.scala.fs.commands.Command
import narok.scala.fs.files.Directory

object FileSystem extends App {

  val root = Directory.ROOT

  val state = State(root, root).show
  io.Source.stdin.getLines().foldLeft(state)((currentState, newLine) => {
    val state = Command.from(newLine).apply(currentState)
    state.show
  })
}
