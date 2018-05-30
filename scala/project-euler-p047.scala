/**
  * Problem 47. Distinct primes factors
  *
  * The first two consecutive numbers to have two distinct prime factors are:
  *
  * 14 = 2 × 7
  * 15 = 3 × 5
  *
  * The first three consecutive numbers to have three distinct prime factors are:
  *
  * 644 = 2² × 7 × 23
  * 645 = 3 × 5 × 43
  * 646 = 2 × 17 × 19.
  *
  * Find the first four consecutive integers to have four distinct prime factors each. What is the first of these numbers?
  *
  * https://projecteuler.net/problem=47
  **/

val PRIME_FACTORS = 4

val MAX = 1000000

var primes = Array.fill[Boolean](MAX)(true)

for (i <- 2 until primes.length - 1) {
  if (primes(i)) {
    var j = i.toLong * i
    while (j < MAX) {
      primes(j.toInt) = false
      j += i
    }
  }
}

def distinctPrimeFactors(x: Int): Int = {
  var factors = collection.mutable.Map[Int, Int]()

  var i = 2
  var n = x
  while (!primes(n)) {
    while (n % i == 0) {
      n = n / i
      if (!(factors isDefinedAt i)) {
        factors += (i -> 1)
      }
    }

    do {
      i += 1
    } while(i < MAX && !primes(i))

  }

  factors.size + (if (n > 1) 1 else 0)
}


var i = 2
var streak = 0

while (i < MAX && streak < PRIME_FACTORS) {
  i += 1

  if (distinctPrimeFactors(i) == PRIME_FACTORS) {
    streak += 1
  } else {
    streak = 0
  }

}
(i - streak + 1 to i).foreach(println(_))


/*

134043
134044
134045
134046

real    0m1.166s
user    0m1.207s
sys     0m0.114s


 */