package narok.scala.fs.commands

import narok.scala.fs.core.State

class UnknownCommand extends Command {
  override def apply(state: State): State =
    state.setMessage("Command not found.")
}
