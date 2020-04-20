package narok.scala.fs.commands

import narok.scala.fs.core.State

class Pwd  extends Command {
  override def apply(state: State): State =
    state.setMessage(state.workingDirectory.path)

}
