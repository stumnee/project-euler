/**
  * Problem 50. Consecutive prime sum
  *
  * The prime 41, can be written as the sum of six consecutive primes:
  *
  *   41 = 2 + 3 + 5 + 7 + 11 + 13
  *
  * This is the longest sum of consecutive primes that adds to a prime below one-hundred.
  * The longest sum of consecutive primes below one-thousand that adds to a prime, contains 21 terms, and is equal to 953.
  * Which prime, below one-million, can be written as the sum of the most consecutive primes?
  *
  * https://projecteuler.net/problem=50
  **/

val max = 1000000

var primes = Array.fill[Boolean](max)(true)

// Generate prime numbers up to Max
// using the sieve of Eratosthenes
for (i <- 2 until primes.length - 1) {
  if (primes(i)) {
    var j = i.toLong * i
    while (j < max) {
      primes(j.toInt) = false
      j += i
    }
  }
}


var terms = collection.mutable.ArrayBuffer[Int](2)

var (pNum, mostTerms) = (2, 2)

var (current, sum) = (3, 2)

while (terms.length > 0) {

  // reach to the highest number below max
  while (sum + current < max) {
    terms += current
    sum += current
    do {
      current += 2
    } while (!primes(current))

  }

  // remove terms from the end to get a prime number sum
  while (!primes(sum)) {
    current = terms.remove(terms.length - 1)
    sum -= current
  }


  // do a comparison for most number of terms
  if (terms.length > mostTerms) {
    mostTerms = terms.length
    pNum = sum
  }

  // remove first element
  sum -= terms.remove(0)
}

println(s"prime number ${pNum} with ${mostTerms} terms")

/*

prime number 997651 with 543 terms

real    0m1.122s
user    0m1.078s
sys     0m0.116s


 */

