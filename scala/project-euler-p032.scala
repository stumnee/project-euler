/**
  * Problem 32. Pandigital products
  *
  * We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once; for example, the 5-digit number, 15234, is 1 through 5 pandigital.
  * The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing multiplicand, multiplier, and product is 1 through 9 pandigital.
  * Find the sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through 9 pandigital.
  *
  * https://projecteuler.net/problem=32
  **/

// 123456789

// Not Possible
// . x  . <= ..
// .. x .. <= ....
// ... x ... >= .....

// Possible cases
// ... x .. == ....
// .... x . == ....

val d = Array(1, 2, 3, 4, 5, 6, 7, 8, 9)

def isPandigital(s: String): Boolean = {
  if (s.indexOf("0") > -1) {
    return false
  }

  for (i <- 0 until s.length - 1) {
    if (s.indexOf(s(i), i + 1) > -1) {
      return false
    }
  }
  true
}

var products = collection.mutable.ListBuffer[Int]()

for (multiplicand <- 123 to 9876) {

  // for smaller set of multipliers,
  // this check does not save execution time!
  if (isPandigital(multiplicand.toString)) {
    for (multiplier <- 1 to 98) {

      val product = multiplicand * multiplier

      val digits = multiplicand.toString +
        multiplier.toString +
        product.toString

      if (digits.length == 9 && isPandigital(digits)) {

        println(s"${multiplicand} x ${multiplier} = ${product}")

        products += product

      }
    }
  }
}

println(products.distinct.sum)
/*
138 x 42 = 5796
157 x 28 = 4396
159 x 48 = 7632
186 x 39 = 7254
198 x 27 = 5346
297 x 18 = 5346
483 x 12 = 5796
1738 x 4 = 6952
1963 x 4 = 7852
45228

real    0m1.524s
user    0m1.976s
sys     0m0.182s

 */