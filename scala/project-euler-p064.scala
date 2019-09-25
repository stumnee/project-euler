/**
 * Problem 64. Odd period square root
 *
 * https://projecteuler.net/problem=64
 */

import scala.collection.mutable

/**
 * sqrt(s) + c
 * -----------
 *     d
 * @param s
 * @param c
 * @param d
 */
class Frac (var s: Int, var c: Int, var d: Int) {

  override def toString = {
    " ( %s + %s )/%s ".format(s,c,d)
  }

  override def equals(that: Any): Boolean = {
    that match {
      case that: Frac => {
        that.s == this.s &&
        that.c == this.c &&
        that.d == this.d
      }
      case _ => false
    }
  }

  /**
   * Reciprocate & get rid of the radical in the denominator
   *
   * @return
   */
  def process(): Frac = {
    val s = this.s
    val c = this.c * -1
    val d = (this.s - this.c * this.c) / this.d
    new Frac(s, c, d)
  }

  /**
   * Substraction of a constant
   * @param x
   * @return
   */
  def -(x: Int):Frac = {
    new Frac(s, c - x * d, d)
  }

  /**
   * Solve the expression
   * @return
   */
  def getIntPart: Int = {
    ((Math.sqrt(this.s) + this.c ) / d).toInt
  }
}

val max = 10000
var oddCount = 0

for (n <- 2 to max) {
  val fracs = mutable.Stack[Frac]()
  val d = Math.sqrt(n);
  var i = d.toInt

  // skip square numbers
  if (d != i) {
    var frac = new Frac(n, i, 1)

    while (fracs.indexOf(frac) == -1 ) {
      fracs.push(frac)
      frac = frac.process()
      i = frac.getIntPart
      frac = frac - i;
    }

    // fractions stack includes the original one
    if (fracs.size % 2 == 0) {
      oddCount += 1
    }
  }
}

println(oddCount)

/**

1322

real    0m2.877s
user    0m9.281s
sys     0m0.373s

*/