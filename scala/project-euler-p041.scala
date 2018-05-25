/**
  * Problem 41. Pandigital prime
  *
  * We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once.
  * For example, 2143 is a 4-digit pandigital and is also prime.
  *
  * What is the largest n-digit pandigital prime that exists?
  *
  * https://projecteuler.net/problem=41
  **/


// 1...9 not prime sum of digits 45 therefore divisible by 3
// 1...8 not prime sum of digits 36 therefore divisible by 3

val max = 7654321


def isPandigital(x: Int): Boolean = {
  var digits = Map(0->1)
  var n = x
  while (n > 0) {
    var d = n % 10
    if (digits isDefinedAt d) {
      return false
    }
    digits += (d->1)

    n /= 10
  }

  // make digits start from 1 and sequential
  var max = 0
  for(i <- 1 to 9) {
    if (digits isDefinedAt i) {
      max = i
    }
  }
  return (max == digits.size - 1)
}

var primes = new Array[Boolean](max + 1)

for (i <- 0 until primes.length) {
  primes(i) = true
}

for (i <- 2 until primes.length - 1) {
  if (primes(i)) {
    var j = i.toLong * i
    while (j < max) {
      primes(j.toInt) = false
      j += i
    }
  }
}

var i = max
var largest = 0
while (largest == 0) {
  if (isPandigital(i) && primes(i)) {
    largest = i
  }
  i -= 1
}

println(largest)

/*

7652413

real    0m1.230s
user    0m1.137s
sys     0m0.120s

 */
