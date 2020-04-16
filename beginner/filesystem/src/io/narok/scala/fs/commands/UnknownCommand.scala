package io.narok.scala.fs.commands
import io.narok.scala.fs.core.State

class UnknownCommand extends Command {
  override def apply(state: State): State =
    state.setMessage("Command not found.")
}
