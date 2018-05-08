/**
  * Problem 23. Non-abundant sums
  *
  * A perfect number is a number for which the sum of its proper divisors is exactly equal to the number.
  * For example, the sum of the proper divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.
  *
  * A number n is called deficient if the sum of its proper divisors is less than n and it is called abundant if this sum exceeds n.
  *
  * As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest number that can be written as the sum of
  * two abundant numbers is 24. By mathematical analysis, it can be shown that all integers greater than 28123 can be written
  * as the sum of two abundant numbers. However, this upper limit cannot be reduced any further by analysis even though
  * it is known that the greatest number that cannot be expressed as the sum of two abundant numbers is less than this limit.
  *
  * Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.
  *
  * https://projecteuler.net/problem=23
  **/

var abundantNumbers = scala.collection.mutable.ListBuffer[Int]()
val (min, max) = (12, 28123)

def divisorSum(x: Int): Int = {
  var sum = 1

  for (j <- 2 to x/2) {
    if (x % j == 0) {
      sum += j
    }
  }
  sum
}

//total 6961 abundant numbers
for (i <- min to max - min ) {
  if (divisorSum(i) > i) {
    abundantNumbers += i
  }
}


var (n, sum) = (1, 0)
while (n < min * 2) {
  sum += n
  n += 1
}

while (n < max) {
  var (i, isOk) = (0, true)
  while (isOk && abundantNumbers(i) <= n / 2) {
    if (abundantNumbers.indexOf(n - abundantNumbers(i)) > -1) {
      isOk = false
    }
    i += 1
  }
  if (isOk) {
    sum += n
  }
  n += 1
}
println(sum)
/*
TODO: takes too long!!!!

4179871

real    3m34.519s
user    3m32.831s
sys     0m1.004s

 */