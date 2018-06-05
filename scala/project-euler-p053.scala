/**
  * Problem 53. Combinatoric selections
  *
  * There are exactly ten ways of selecting three from five, 12345:
  * 123, 124, 125, 134, 135, 145, 234, 235, 245, and 345
  * In combinatorics, we use the notation, 5C3 = 10.
  * In general,
  * It is not until n = 23, that a value exceeds one-million: 23C10 = 1144066.
  * How many, not necessarily distinct, values of  nCr, for 1 ≤ n ≤ 100, are greater than one-million?
  *
  * https://projecteuler.net/problem=53
  **/

val threshold = 1000000
val maxN = 100

var f = collection.mutable.Map[Int, BigInt]()

def factorial(x: Int): BigInt = {
  if (f isDefinedAt x) {
    return f(x)
  }
  var n = BigInt(1)
  for (i <- 2 to x) {
    n *= i
  }
  f(x) = n
  n
}

var total = 0
for (n <- 2 to maxN) {
  for (r <- 1 until n) {
    if (factorial(n) / factorial(r)/factorial(n-r) > threshold) {
      total += 1
    }
  }
}

println(total)

/*
4075

real    0m1.113s
user    0m1.094s
sys     0m0.123s

 */