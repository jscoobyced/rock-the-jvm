package narok.scala.fs.commands

import narok.scala.fs.core
import narok.scala.fs.core.State
import narok.scala.fs.files.Directory

class Rm(entry: String) extends Command {
  override def apply(state: State): State = {
    val workingDirectory = state.workingDirectory
    val absolutePath =
      if (entry.startsWith(Directory.SEPARATOR)) entry
      else if (workingDirectory.isRoot) workingDirectory.path + entry
      else workingDirectory.path + Directory.SEPARATOR + entry

    if (Directory.ROOT_PATH.equals(absolutePath))
      state.setMessage(s"You can't remove ${Directory.ROOT_PATH}")
    else
      doRm(state, absolutePath)
  }

  private def doRm(state: State, path: String): State = {

    def rmHelper(currentDirectory: Directory, path: List[String]): Directory = {
      if (path.isEmpty) currentDirectory
      else if (path.tail.isEmpty) currentDirectory.removeEntry(path.head)
      else {
        val nextDirectory = currentDirectory.findEntry(path.head)
        if (!nextDirectory.isDirectory) currentDirectory
        else {
          val newNextDirectory = rmHelper(nextDirectory.asDirectory, path.tail)
          if (newNextDirectory == nextDirectory) currentDirectory
          else currentDirectory.replaceEntry(path.head, newNextDirectory)
        }
      }
    }

    val tokens = path.substring(1).split(Directory.SEPARATOR).toList

    val newRoot: Directory = rmHelper(state.root, tokens)

    if (newRoot == state.root)
      state.setMessage(s"$path: no such file or directory.")
    else
      core.State(newRoot, newRoot.findDescendant(state.workingDirectory.path.substring(1)))
  }
}
