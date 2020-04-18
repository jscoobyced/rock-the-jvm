package io.narok.scala.fs.commands

import java.text.SimpleDateFormat
import java.util.Date

import io.narok.scala.fs.core.State
import io.narok.scala.fs.files.DirEntry

class Ls extends Command {

  def createNiceOutput(contents: List[DirEntry]): String = {
    if (contents.isEmpty) ""
    else {
      val entry = contents.head
      entry.getShortType + " " + dateFormat(entry.getLastModifiedTime) + " "  + entry.name + System.lineSeparator() + createNiceOutput(contents.tail)
    }
  }

  override def apply(state: State): State = {
    val contents = state.workingDirectory.content
    val niceOutput = createNiceOutput(contents)
    state.setMessage(niceOutput.substring(0, niceOutput.length - Math.min(niceOutput.length, System.lineSeparator().length)))
  }

  private def dateFormat(date: Date): String = {
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm")
    dateFormat.format(date)
  }
}
