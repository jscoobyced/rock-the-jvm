package io.narok.scala.fs.files

import io.narok.scala.fs.core.FileSystemException

class File(override val parentPath: String, override val name: String, contents: String)
  extends DirEntry(parentPath, name) {

  override def asDirectory: Directory = throw new FileSystemException("A File cannot be converted to a directory.")

  def asFile: File = this

  override def getShortType: String = "-"

  override def getType: String = "File"
}

object File {
  def empty(parentPath: String, name: String): File =
    new File(parentPath, name, "")
}