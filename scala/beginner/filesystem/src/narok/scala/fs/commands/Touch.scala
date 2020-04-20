package narok.scala.fs.commands

import narok.scala.fs.core.State
import narok.scala.fs.files.{DirEntry, File}

class Touch(name: String) extends CreateEntry(name) {

  override def createSpecificEntry(state: State): DirEntry =
    File.empty(state.workingDirectory.path, name)
}
