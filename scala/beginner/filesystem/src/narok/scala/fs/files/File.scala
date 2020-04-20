package narok.scala.fs.files

import narok.scala.fs.core.FileSystemException

class File(override val parentPath: String, override val name: String, val content: String)
  extends DirEntry(parentPath, name) {

  override def asDirectory: Directory = throw new FileSystemException("A File cannot be converted to a directory.")

  def asFile: File = this

  override def getShortType: String = "-"

  override def getType: String = "File"

  override def isDirectory: Boolean = false

  override def isFile: Boolean = true

  def setContent(newContent: String): File =
    new File(parentPath, name, newContent)

  def appendContent(newContent: String): File =
    setContent(content + "\n" + newContent)
}

object File {
  def empty(parentPath: String, name: String): File =
    new File(parentPath, name, "")
}