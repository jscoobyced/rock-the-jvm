package io.narok.scala.fs.commands
import io.narok.scala.fs.core.State

class Exit extends Command{
  override def apply(state: State): State = state.off()
}
