/**
  * Problem 46. Goldbach's other conjecture
  *
  * It was proposed by Christian Goldbach that every odd composite number can be written as the sum of a prime and twice a square.
  * 9 = 7 + 2×12
  * 15 = 7 + 2×22
  * 21 = 3 + 2×32
  * 25 = 7 + 2×32
  * 27 = 19 + 2×22
  * 33 = 31 + 2×12
  * It turns out that the conjecture was false.
  * What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?
  *
  * https://projecteuler.net/problem=46
  **/

val max = 1000000

var primes = Array.fill[Boolean](max)(true)

for (i <- 2 until primes.length - 1) {
  if (primes(i)) {
    var j = i.toLong * i
    while (j < max) {
      primes(j.toInt) = false
      j += i
    }
  }
}

def twiceASquare(n: Int) : Boolean = {
  val sq = math.sqrt(n / 2)
  sq == sq.toInt
}

def isSumOfAPrimeAndTwiceASquare(n: Int): Boolean = {
  var p = 3
  while (p < n) {
    if (primes(p) && twiceASquare(n - p)) {
      return true
    }
    // get next prime number
    p += 2
  }
  return false
}

var n = 33

while (isSumOfAPrimeAndTwiceASquare(n)) {
  do {
    n += 2
  } while (primes(n))
}

println(n)

/*
5777

real    0m1.161s
user    0m1.018s
sys     0m0.113s

 */