package narok.scala.fs.commands

import narok.scala.fs.core
import narok.scala.fs.core.State
import narok.scala.fs.files.{DirEntry, Directory}

abstract class CreateEntry(entryName: String) extends Command {

  override def apply(state: State): State = {
    val workingDirectory = state.workingDirectory
    if (workingDirectory.hasEntry(entryName)) {
      state.setMessage(s"$entryName already exists.")
    }
    else if (entryName.contains(Directory.SEPARATOR)) {
      state.setMessage(s"$entryName must not contain ${Directory.SEPARATOR} character.")
    } else if (checkIllegal(entryName)) {
      state.setMessage(s"$entryName: illegal entry name.")
    } else {
      doCreateEntry(state)
    }
  }

  private def checkIllegal(name: String): Boolean = {
    name.contains(".")
  }

  private def doCreateEntry(state: State): State = {
    def updateStructure(currentDirectory: Directory, path: List[String], newEntry: DirEntry): Directory = {
      if(path.isEmpty) currentDirectory.addEntry(newEntry)
      else {
        val oldEntry = currentDirectory.findEntry(path.head).asDirectory
        currentDirectory.replaceEntry(oldEntry.name, updateStructure(oldEntry, path.tail, newEntry))
      }
    }

    val workingDirectory = state.workingDirectory

    val allDirectoriesInPath = workingDirectory.getAllDirectoriesInPath
    val newEntry = createSpecificEntry(state)
    val newRoot = updateStructure(state.root, allDirectoriesInPath, newEntry)
    val newWorkingDirectory = newRoot.findDescendant(allDirectoriesInPath)
    core.State(newRoot, newWorkingDirectory)
  }

  def createSpecificEntry(state: State): DirEntry
}
