package narok.scala.fs.files

import narok.scala.fs.core.FileSystemException

import scala.annotation.tailrec

class Directory(override val parentPath: String, override val name: String, val content: List[DirEntry])
  extends DirEntry(parentPath, name) {

  override def asDirectory: Directory = this

  override def getType: String = "Directory"

  override def asFile: File = throw new FileSystemException("A Directory cannot be converted to a File.")

  override def getShortType: String = "d"

  def isRoot: Boolean = parentPath.isEmpty

  override def isDirectory: Boolean = true

  override def isFile: Boolean = false

  def replaceEntry(entryName: String, newEntry: DirEntry): Directory =
    new Directory(parentPath, name, content.filter(entry => !entry.name.equals(entryName)) :+ newEntry)

  def findEntry(entryName: String): DirEntry = {
    @tailrec
    def findEntryHelper(name: String, contentList: List[DirEntry]): DirEntry = {
      if (contentList.isEmpty) null
      else if (contentList.head.name.equals(name)) contentList.head
      else findEntryHelper(name, contentList.tail)
    }

    findEntryHelper(entryName, content)
  }

  def findDescendant(path: List[String]): Directory = {
    if (path.isEmpty) this
    else findEntry(path.head).asDirectory
      .findDescendant(path.tail)
  }

  def findDescendant(relativePath: String): Directory = {
    if (relativePath.isEmpty) this
    else findDescendant(relativePath.split(Directory.SEPARATOR).toList)
  }

  def removeEntry(entryName: String): Directory = {
    if (!hasEntry(entryName)) this
    else new Directory(parentPath, name, content.filter(n => !n.name.equals(entryName)))
  }

  def getAllDirectoriesInPath: List[String] =
    path.substring(Directory.SEPARATOR.length)
      .split(Directory.SEPARATOR)
      .toList
      .filter(x => !x.isEmpty)

  def addEntry(newEntry: DirEntry): Directory =
    new Directory(parentPath, name, content :+ newEntry)

  def hasEntry(name: String): Boolean = findEntry(name) != null
}

object Directory {
  val SEPARATOR = "/"
  val ROOT_PATH = "/"
  val DOT = "."
  val DOTDOT = ".."

  def empty(parentPath: String, name: String) =
    new Directory(parentPath, name, List())

  def ROOT: Directory = Directory.empty("", "")
}