package narok.scala.fs.commands

import narok.scala.fs.core.State
import narok.scala.fs.files.{DirEntry, Directory}

class MkDir(name: String) extends CreateEntry(name) {

  override def createSpecificEntry(state: State): DirEntry =
    Directory.empty(state.workingDirectory.path, name)
}
