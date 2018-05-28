/**
  * Problem 45. Triangular, pentagonal, and hexagonal
  *
  * Triangle, pentagonal, and hexagonal numbers are generated by the following formulae:
  *
  * Triangle	 	Tn=n(n+1)/2	 	1, 3, 6, 10, 15, ...
  * Pentagonal	 	Pn=n(3n−1)/2	 	1, 5, 12, 22, 35, ...
  * Hexagonal	 	Hn=n(2n−1)	 	1, 6, 15, 28, 45, ...
  *
  * It can be verified that T285 = P165 = H143 = 40755.
  * Find the next triangle number that is also pentagonal and hexagonal.
  *
  * https://projecteuler.net/problem=45
  **/


// n(3n - 1)/2 = x
// 3n^2 - n = 2x
// 3n^2 - n - 2x = 0
// x can be found using quadratic equation
// a = 3, b = -1, c = 2x
def isPentagonal(x: Long): Boolean = {
  val n = math.sqrt(1 + 24 * x) + 1
  n % 6 == 0
}

// n(2n - 1) = x
// 2n^2 - n = x
// 2n^2 - n - x = 0
// x can be found using quadratic equation
// a = 2, b = -1, c = x
def isHexagonal(x: Long): Boolean = {
  val n = math.sqrt(1 + 8 * x) + 1
  n % 4 == 0
}

var found = 0
val max = 3
var n: Long = 1

while (found < max) {
  var t: Long = n * (n + 1) / 2
  if (isHexagonal(t) && isPentagonal(t)) {
    found += 1
    println(t)
  }
  n += 1
}

/*

1
40755
1533776805

real    0m1.074s
user    0m0.972s
sys     0m0.111s

 */