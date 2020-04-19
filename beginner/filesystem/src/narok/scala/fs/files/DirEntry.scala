package narok.scala.fs.files

import java.util.Date

abstract class DirEntry(val parentPath: String, val name: String, val getLastModifiedTime: Date = new Date()) {

  def path: String = {
    val separatorIfNecessary =
      if(Directory.ROOT_PATH.equals(parentPath)) ""
      else Directory.SEPARATOR
    parentPath + separatorIfNecessary + name
  }

  def asDirectory: Directory

  def asFile: File

  def getType: String

  def getShortType: String

  def getCreationTime: Date = new Date()

  def isDirectory: Boolean

  def isFile: Boolean

}
