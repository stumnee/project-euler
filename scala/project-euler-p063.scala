/**
  * Problem 63. Powerful digit counts
  *
  * The 5-digit number, 16807=7^5, is also a fifth power. Similarly, the 9-digit number, 134217728=8^9, is a ninth power.
  *
  * How many n-digit positive integers exist which are also an nth power?
  *
  * https://projecteuler.net/problem=63
  **/


var count = 0

var pow = 1

var subCount = 0

do {
  subCount = (1 to 9).map{x => if (BigInt(x).pow(pow).toString.length == pow) 1 else 0}.sum
  count += subCount
  pow += 1
} while (subCount > 0)


println(count)

/*

49

real    0m1.107s
user    0m1.045s
sys     0m0.130s


 */