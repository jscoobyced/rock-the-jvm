package exercises.part2oop

class Novel(name: String, yearOfRelease: Int, var author: Writer) {

  def authorAge = yearOfRelease - author.year

  def isWrittenBy(author: Writer) =
    this.author == author

  def copy(year: Int) = new Novel(name, year, author)
}