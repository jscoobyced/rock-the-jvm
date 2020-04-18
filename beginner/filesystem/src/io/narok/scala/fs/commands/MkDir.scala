package io.narok.scala.fs.commands

import io.narok.scala.fs.core.State
import io.narok.scala.fs.files.{DirEntry, Directory}

class MkDir(name: String) extends CreateEntry(name) {

  override def createSpecificEntry(state: State): DirEntry =
    Directory.empty(state.workingDirectory.path, name)
}
