package narok.scala.fs.commands

import narok.scala.fs.core.State

class Cat(filename: String) extends Command {
  override def apply(state: State): State = {
    val workingDirectory = state.workingDirectory

    val dirEntry = workingDirectory.findEntry(filename)
    if (dirEntry == null || !dirEntry.isFile)
      state.setMessage(s"$filename: no such file.")
    else
      state.setMessage(dirEntry.asFile.content)
  }
}
