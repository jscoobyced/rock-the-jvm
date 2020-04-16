package io.narok.scala.fs.commands

import io.narok.scala.fs.core.State
import io.narok.scala.fs.files.{DirEntry, Directory}

class MkDir(name: String) extends Command {

  override def apply(state: State): State = {
    val workingDirectory = state.workingDirectory
    if (workingDirectory.hasEntry(name)) {
      state.setMessage(s"$name already exists.")
    }
    else if (name.contains(Directory.SEPARATOR)) {
      state.setMessage(s"$name must not contain ${Directory.SEPARATOR} character.")
    } else if (checkIllegal(name)) {
      state.setMessage(s"$name: illegal entry name.")
    } else {
      doMkdir(state, name)
    }
  }

  private def checkIllegal(name: String): Boolean = {
    name.contains(".")
  }

  private def doMkdir(state: State, name: String): State = {
    def updateStructure(currentDirectory: Directory, path: List[String], newEntry: DirEntry): Directory = {
      if(path.isEmpty) currentDirectory.addEntry(newEntry)
      else {
        val oldEntry = currentDirectory.findEntry(path.head).asDirectory
        currentDirectory.replaceEntry(oldEntry.name, updateStructure(oldEntry, path.tail, newEntry))
      }
    }

    val workingDirectory = state.workingDirectory

    val allDirectoriesInPath = workingDirectory.getAllDirectoriesInPath()
    val newDirectory = Directory.empty(workingDirectory.path, name)
    val newRoot = updateStructure(state.root, allDirectoriesInPath, newDirectory)
    val newWorkingDirectory = newRoot.findDescendant(allDirectoriesInPath)
    State(newRoot, newWorkingDirectory)
  }
}
