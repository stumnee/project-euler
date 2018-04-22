/**
  * Problem 3. Largest prime factor
  *
  * The prime factors of 13195 are 5, 7, 13 and 29.
  * What is the largest prime factor of the number 600851475143 ?
  *
  * https://projecteuler.net/problem=3
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

var n = 600851475143L
var i = 3

while (i <= n) {
  if (isPrime(i) && n % i == 0) {
    print(i + " ")
    n = n / i
  }
  i = i + 2
}
