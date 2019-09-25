/**
 * Problem 65. Convergents of e
 *
 * https://projecteuler.net/problem=65
 */

import scala.collection.mutable

class Frac(var numerator: BigInt, var denominator: BigInt) {
  def +(x: BigInt): Frac = {
    new Frac(this.numerator + this.denominator * x, this.denominator)
  }

  def reciprocate(): Frac = {
    new Frac(this.denominator, this.numerator)
  }

  override def toString: String = {
    this.numerator + "/" + this.denominator
  }
}

val n = 100

val stack = mutable.Stack[BigInt]()

stack.push(2)
stack.push(1)

for (i <- 0 until n - 2) {
  var x = 1
  if (i  % 3 == 0) {
    x = 2 * (i / 3 + 1)
  }
  stack.push(x)
}

var frac = new Frac(0L, 1L)

while (!stack.isEmpty) {
  frac = frac + stack.pop()
  frac = frac.reciprocate()
}

println(frac.denominator.toString().map(c => c - 48).sum)

/*

272

real    0m3.067s
user    0m9.722s
sys     0m0.431s

 */