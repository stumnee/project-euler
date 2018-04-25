/**
  * Problem 7. 10001st prime
  *
  * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
  *
  * What is the 10 001st prime number?
  *
  * https://projecteuler.net/problem=7
  *
  */

// Change the data type Int to Long according to the nth value
var primes = scala.collection.mutable.ListBuffer[Int](2)

var i = 3

val nth = 10001

while (primes.size < nth) {
  if (primes.forall(p => i % p != 0)) {
    primes += i
  }
  i = i + 2
}
println(primes.last)