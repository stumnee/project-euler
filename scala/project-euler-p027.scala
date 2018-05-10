/**
  * Problem 27. Quadratic primes
  *
  * Euler discovered the remarkable quadratic formula:
  *
  *         n² + n + 41
  *
  * It turns out that the formula will produce 40 primes for the consecutive integer values $0 \le n \le 39$.
  * However, when $n = 40, 40² + 40 + 41 = 40(40 + 1) + 41$ is divisible by 41, and certainly when $n = 41, 412 + 41 + 41$ is clearly divisible by 41.
  *
  * The incredible formula n² - 79n + 1601 was discovered, which produces 80 primes for the consecutive values $0 \le n \le 79$. The product of the coefficients, −79 and 1601, is −126479.
  *
  * Considering quadratics of the form:
  *
  *   n² + an + b, where |a| < 1000 and |b|<1000
  *
  *   where |n| is the modulus/absolute value of n
  *   e.g. |11| = 11 and |-4| = 4
  *
  * Find the product of the coefficients, a and b, for the quadratic expression that produces the maximum number of primes for consecutive values of $n$, starting with n = 0.
  *
  * https://projecteuler.net/problem=27
  **/

// Generate prime numbers

var primes = scala.collection.mutable.ListBuffer[Int](2)

val abLimit = 1000

var maxPrimes = 1
var s = "" // most prime string

var i = 3
while (primes.last < 100 * abLimit) {
  if (primes.forall(p => i % p != 0)) {
    primes += i
  }
  i = i + 2
}

List(-1, 1).foreach { // Try both positive and negative prime numbers
  bMult =>
    // at n = 0, b is a prime number
    primes.filter(_ <= abLimit).foreach { bb =>
      val b = bb * bMult

      primes.filter(_ - 1 - b > -abLimit).filter(_ - 1 - b < abLimit).foreach { p =>
        val a = p - 1 - b //a value at n = 1
        var (primeCount, n) = (2, 2)

        // calculate prime count for given a and b
        while (primes.indexOf(n * n + a * n + b) > -1) {
          n += 1
          primeCount += 1
        }

        if (primeCount > maxPrimes) {
          maxPrimes = primeCount
          s = s"a= ${a} b= ${b} product= ${a * b} primes= ${primeCount}"
        }
      }
    }
}
println(s)

/*

a= -61 b= 971 product= -59231 primes= 71

real    0m5.096s
user    0m5.065s
sys     0m0.154s

 */