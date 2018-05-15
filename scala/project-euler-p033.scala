import scala.collection.mutable.ListBuffer

/**
  * Problem 33. Digit cancelling fractions
  *
  * The fraction 49/98 is a curious fraction, as an inexperienced mathematician in attempting to simplify
  * it may incorrectly believe that 49/98 = 4/8, which is correct, is obtained by cancelling the 9s.
  *
  * We shall consider fractions like, 30/50 = 3/5, to be trivial examples.
  *
  * There are exactly four non-trivial examples of this type of fraction, less than one in value,
  * and containing two digits in the numerator and denominator.
  *
  * If the product of these four fractions is given in its lowest common terms, find the value of the denominator.
  *
  * https://projecteuler.net/problem=33
  **/


/**
  * Very confusing worded problem to begin with
  *
  * numerator     num      num * common digit
  * ----------- = ----- =  -----------------
  * denominator   denom    commom digit * denom
  *
  * numerator < denominator (less than one in value) ???????
  *
  */

var fractions = ListBuffer[(Int, Int, Int, Int)]()

def addIfCancelling(numerator: Int, denominator: Int, num: Int, denom: Int) = {
  if (num != denom && numerator < denominator && denom * numerator == num * denominator ) {
    fractions += ((numerator, denominator, num, denom))
  }
}

for (numerator <- 10 to 99) {
  for (denom <- 1 to 9) {

    // ac/cb
    addIfCancelling(numerator, numerator % 10 * 10 + denom, numerator / 10, denom)

    // ca/bc
    addIfCancelling(numerator, 10 * denom + numerator / 10, numerator % 10, denom)

  }
}
var (nn: Int, dd: Int) = (1, 1)
fractions.foreach {
  fraction =>

  val (numerator, denominator, num, denom) = fraction
  println(s"${numerator}/${denominator} = ${num}/${denom}")

  nn *= num
  dd *= denom
}

println(s"${nn}/${dd}")
/*
16/64 = 1/4
19/95 = 1/5
26/65 = 2/5
49/98 = 4/8
8/800

real    0m1.201s
user    0m1.011s
sys     0m0.110s

Answer: 8/800 = 1/100
 */