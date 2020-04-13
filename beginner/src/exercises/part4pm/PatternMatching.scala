package exercises.part4pm

object PatternMatching extends App {

  trait Expr

  case class Number(n: Int) extends Expr

  case class Sum(e1: Expr, e2: Expr) extends Expr

  case class Prod(e1: Expr, e2: Expr) extends Expr

  def show(e: Expr): String = e match {
    case Number(n) => s"$n"
    case Sum(e1, e2) => show(e1) + " + " + show(e2)
    case Prod(e1, e2) => {
      def maybeShowParenthesis(exp: Expr) = exp match {
        case Prod(_, _) => show(exp)
        case Number(_) => show(exp)
        case _ => "(" + show(exp) + ")"
      }
      maybeShowParenthesis(e1) + " * " + maybeShowParenthesis(e2)
    }
    case _ => "No idea what's going on here."
  }

  val sum1 = Sum(Number(2), Number(3))
  val sum2 = Sum(Sum(Number(2), Number(3)), Number(4))
  val sum3 = Sum(Prod(Number(2), Number(3)), Number(4))
  val sum4 = Prod(Sum(Number(4), Number(5)), Sum(Number(2), Number(3)))
  val prod1 = Prod(Sum(Number(2), Number(3)), Number(4))
  println(show(sum1))
  println(show(sum2))
  println(show(sum3))
  println(show(sum4))
  println(show(prod1))

}
