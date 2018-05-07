/**
  * Problem 20. Factorial digit sum
  *
  * n! means n × (n − 1) × ... × 3 × 2 × 1
  *
  * For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
  *
  * Find the sum of the digits in the number 100!
  *
  * https://projecteuler.net/problem=20
  **/

val n = 100


var f:BigInt = 1

for (i <- 2 to n) {
  f *= i
}

println(f.toString.map(_.toInt - '0').sum)