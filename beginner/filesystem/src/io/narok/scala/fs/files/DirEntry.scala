package io.narok.scala.fs.files

import java.util.Date

abstract class DirEntry(val parentPath: String, val name: String, val getLastModifiedTime: Date = new Date()) {

  def path: String = parentPath + Directory.SEPARATOR + name

  def asDirectory: Directory

  def asFile: File

  def getType: String

  def getShortType: String

  def getCreationTime: Date = new Date()
}
