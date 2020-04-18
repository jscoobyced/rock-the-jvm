package io.narok.scala.fs.commands

import io.narok.scala.fs.core.State

trait Command {
  def apply(state: State): State
}

object Command {
  val MKDIR = "mkdir"
  val EXIT = "exit"
  val LS = "ls"
  val PWD = "pwd"
  val TOUCH = "touch"

  def emptyCommand: Command = (state: State) => state.setMessage("")

  def incompleteCommand(name: String): Command = (state: State) => {
    state.setMessage(s"$name: incomplete command.")
  }

  def from(input: String): Command = {
    val tokens = input.split(" ")

    if (EXIT.equals(tokens(0))) new Exit
    else if (input.isEmpty || tokens.isEmpty) emptyCommand
    else if (MKDIR.equals(tokens(0))) {
      if (tokens.length < 2) incompleteCommand(MKDIR)
      else new MkDir(tokens(1))
    }
    else if (TOUCH.equals(tokens(0))) {
      if (tokens.length < 2) incompleteCommand(TOUCH)
      else new Touch(tokens(1))
    }
    else if (LS.equals(tokens(0))) new Ls
    else if (PWD.equals(tokens(0))) new Pwd
    else new UnknownCommand
  }
}
