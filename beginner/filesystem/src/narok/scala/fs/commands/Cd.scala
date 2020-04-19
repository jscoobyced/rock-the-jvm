package narok.scala.fs.commands

import narok.scala.fs.core
import narok.scala.fs.core.State
import narok.scala.fs.files.{DirEntry, Directory}

import scala.annotation.tailrec

class Cd(directoryName: String) extends Command {
  override def apply(state: State): State = {
    val root = state.root
    val workingDirectory = state.workingDirectory
    val absolutePath =
      if (directoryName.startsWith(Directory.SEPARATOR)) directoryName
      else if (workingDirectory.isRoot) workingDirectory.path + directoryName
      else workingDirectory.path + Directory.SEPARATOR + directoryName
    val destinationDirectory = doFindEntry(root, absolutePath)
    if(destinationDirectory == null || !destinationDirectory.isDirectory)
      state.setMessage(s"$directoryName: no such directory.")
    else core.State(root, destinationDirectory.asDirectory)
  }

  private def doFindEntry(root: Directory, destinationDirectory: String): DirEntry = {
    @tailrec
    def findEntryHelper(currentDirectory: Directory, path: List[String]): DirEntry =
      if(path.isEmpty || path.head.isEmpty) currentDirectory
      else if (path.tail.isEmpty) currentDirectory.findEntry(path.head)
      else {
        val nextEntry = currentDirectory.findEntry(path.head)
        if(nextEntry == null || !nextEntry.isDirectory) null
        else findEntryHelper(nextEntry.asDirectory, path.tail)
      }
    @tailrec
    def collapseTokens(path: List[String], result: List[String]): List[String] =
      if(path.isEmpty) result
      else if (Directory.DOT.equals(path.head)) collapseTokens(path.tail, result)
      else if (Directory.DOTDOT.equals(path.head)) {
        if(result.isEmpty) null
        else collapseTokens(path.tail, result.init)
      } else collapseTokens(path.tail, result:+ path.head)

    val tokens: List[String] = destinationDirectory.substring(1).split(Directory.SEPARATOR).toList
    val newTokens = collapseTokens(tokens, List())

    if(newTokens == null) null
    else findEntryHelper(root, newTokens)
  }
}
