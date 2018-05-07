import scala.collection.mutable

/**
  * Problem 21. Amicable numbers
  *
  * Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
  * If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are called amicable numbers.
  * For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.
  * Evaluate the sum of all the amicable numbers under 10000.
  *
  * https://projecteuler.net/problem=21
  **/

def divisorSum(x: Int): Int = {
  var sum = 1

  for (j <- 2 to x/2) {
    if (x % j == 0) {
      sum += j
    }
  }
  sum
}

val n = 10000
var map = mutable.Map[Int, Int]()

//find all the sum of divisors for numbers between 2..n-1
for (i <- 2 until n) {
  map += (i -> divisorSum(i))
}

var sumOfAmicables = 0

for ((k, v) <- map) {
  if (map.keySet.exists(_ == v) && k == map(v) && k != v) {
    sumOfAmicables += k
  }
}

println(sumOfAmicables)

