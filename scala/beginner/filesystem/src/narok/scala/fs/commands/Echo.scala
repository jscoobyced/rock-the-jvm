package narok.scala.fs.commands

import narok.scala.fs.core
import narok.scala.fs.core.State
import narok.scala.fs.files.{Directory, File}

import scala.annotation.tailrec


class Echo(arguments: Array[String]) extends Command {

  override def apply(state: State): State = {
    if (arguments.isEmpty) state
    else if (arguments.length == 1) state.setMessage(arguments(0))
    else {
      val operator = arguments(arguments.length - 2)
      val filename = arguments(arguments.length - 1)
      val content = createContent(arguments, arguments.length - 2).substring(1)

      if (">>".equals(operator))
        doEcho(state, content, filename, append = true)
      else if (">".equals(operator))
        doEcho(state, content, filename, append = false)
      else
        state.setMessage(createContent(arguments, arguments.length).substring(1))
    }
  }

  def getRootAfterEcho(currentDirectory: Directory, path: List[String], content: String, append: Boolean): Directory = {
    if (path.isEmpty) currentDirectory
    else if (path.tail.isEmpty) {
      val dirEntry = currentDirectory.findEntry(path.head)
      if (dirEntry == null)
        currentDirectory.addEntry(new File(currentDirectory.path, path.head, content))
      else if (dirEntry.isDirectory) currentDirectory
      else if (append)
        currentDirectory.replaceEntry(path.head, dirEntry.asFile.appendContent(content))
      else currentDirectory.replaceEntry(path.head, dirEntry.asFile.setContent(content))
    } else {
      val nextDirectory = currentDirectory.findEntry(path.head).asDirectory
      val newNextDirectory = getRootAfterEcho(nextDirectory, path.tail, content, append)
      if(newNextDirectory == nextDirectory) currentDirectory
      else currentDirectory.replaceEntry(path.head, newNextDirectory)
    }
  }

  def doEcho(state: State, content: String, filename: String, append: Boolean): State = {
    if (filename.contains(Directory.SEPARATOR))
      state.setMessage("echo: filename cannot contain separators.")
    else {
      val newRoot: Directory = getRootAfterEcho(state.root, state.workingDirectory.getAllDirectoriesInPath :+ filename, content, append)
      if (newRoot.equals(state.root))
        state.setMessage(s"$filename: no such file.")
      else {
        core.State(newRoot, newRoot.findDescendant(state.workingDirectory.getAllDirectoriesInPath))
      }
    }
  }

  def createContent(arguments: Array[String], topIndex: Int): String = {
    @tailrec
    def createContentHelper(currentIndex: Int, accumulator: String): String = {
      if (currentIndex >= topIndex) accumulator
      else createContentHelper(currentIndex + 1, accumulator + " " + arguments(currentIndex))
    }

    createContentHelper(0, "")
  }
}
