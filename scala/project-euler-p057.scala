/**
  * Problem 57. Square root convergents
  *
  * It is possible to show that the square root of two can be expressed as an infinite continued fraction.
  * √ 2 = 1 + 1/(2 + 1/(2 + 1/(2 + ... ))) = 1.414213...
  * By expanding this for the first four iterations, we get:
  * 1 + 1/2 = 3/2 = 1.5
  * 1 + 1/(2 + 1/2) = 7/5 = 1.4
  * 1 + 1/(2 + 1/(2 + 1/2)) = 17/12 = 1.41666...
  * 1 + 1/(2 + 1/(2 + 1/(2 + 1/2))) = 41/29 = 1.41379...
  * The next three expansions are 99/70, 239/169, and 577/408, but the eighth expansion, 1393/985, is the first example where the number of digits in the numerator exceeds the number of digits in the denominator.
  * In the first one-thousand expansions, how many fractions contain a numerator with more digits than denominator?
  *
  * https://projecteuler.net/problem=57
  **/

def expansionSqRoot2Expr(n: Int): (BigInt, BigInt) = {
  var ex = (BigInt(0), BigInt(1))

  for (i <- 1 to  n) {
    ex = (ex._2, 2 * ex._2 + ex._1)
  }
  (ex._1 + ex._2, ex._2)
}

println((1 to 1000).map { n =>
  expansionSqRoot2Expr(n)
}.map{t=>
  if (t._1.toString.length > t._2.toString.length)
    1
  else
    0
}.sum)

/*
153

real    0m1.244s
user    0m1.541s
sys     0m0.143s

 */