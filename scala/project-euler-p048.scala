/**
  * Problem 48. Self powers
  *
  * The series, 11 + 22 + 33 + ... + 1010 = 10405071317.
  *
  * Find the last ten digits of the series, 11 + 22 + 33 + ... + 10001000.
  *
  * https://projecteuler.net/problem=48
  **/


println((1 to 1000)
  .map(i => BigInt(i).pow(i))
  .sum
  .toString
  .takeRight(10))


/*

9110846700

real    0m1.111s
user    0m1.170s
sys     0m0.121s


 */