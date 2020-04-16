package io.narok.scala.fs.core

import java.util.Scanner

import io.narok.scala.fs.commands.Command
import io.narok.scala.fs.files.Directory

object FileSystem extends App {

  val root = Directory.ROOT
  var state = State(root, root)
  val scanner = new Scanner(System.in)

  while (state.on) {
    state.show
    val input = scanner.nextLine()
    state = Command.from(input).apply(state)
  }
  state.show
}
