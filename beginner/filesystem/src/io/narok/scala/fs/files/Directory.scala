package io.narok.scala.fs.files

import scala.annotation.tailrec

class Directory(override val parentPath: String, override val name: String, val content: List[DirEntry])
  extends DirEntry(parentPath, name) {

  override def asDirectory: Directory = this

  override def getType: String = "Directory"

  override def getShortType: String = "d"

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

  def getAllDirectoriesInPath(): List[String] =
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

  def empty(parentPath: String, name: String) =
    new Directory(parentPath, name, List())

  def ROOT: Directory = Directory.empty("", "")
}