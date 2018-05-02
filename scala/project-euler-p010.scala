/**
  * Problem 10. Summation of primes
  *
  * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
  *
  * Find the sum of all the primes below two million.
  *
  * https://projecteuler.net/problem=10
  */

var primes = scala.collection.mutable.ListBuffer[Int](3)


var max = 2000000
var i = 5

while (i < max) {
  if (primes.forall(p => i % p != 0)) {
    primes += i
  }
  i = i + 2
}


// primes.sum won't work as it calculates in Int not Long
var sum = 2L
primes.foreach(sum += _)

//142913828922

println("Sum of primes: " + sum)

println("Total Primes: " + primes.size)

