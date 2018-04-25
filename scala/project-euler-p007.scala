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


var primes = scala.collection.mutable.ListBuffer[Long](2)

def isPrime(x: Long): Boolean = {
  var isOk = true

  primes.foreach(p => if (x % p == 0) isOk = false)
  if (isOk) {
    primes += x
  }
  isOk
}

var i = 3

while (primes.size < 10001) {
  isPrime(i)
  i = i + 2
}
println(primes.last)