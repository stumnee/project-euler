/**
  * Problem 30. Digit fifth powers
  *
  * Surprisingly there are only three numbers that can be written as the sum of fourth powers of their digits:
  *
  *   16384 = 1^4 + 6^4 + 3^4 + 4^4
  *
  *
  * As 1 = 14 is not a sum it is not included.
  * The sum of these numbers is 1634 + 8208 + 9474 = 19316.
  * Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.
  *
  * https://projecteuler.net/problem=30
  **/


/*
  9^5 = 59049

  +------------------------------+
  | Digits |   min  |  9^5*d max |
  +------------------------------+
  |   1    |      1 |   59049    |
  |   2    |     10 |   118098   |
  |   3    |    100 |   177147   |
  |   4    |   1000 |   236196   |
  |   5    |  10000 |   295245   |
  |   6    | 100000 |   354294   |
  |   7    |1000000 |   413343   |<- Not possible
  +------------------------------+

 */


var total = 0

// 1^5 no counted, start with 2

for (n <- 2 to 999999) {
  var x = n
  var sum = 0
  while (x > 0) {
    sum += math.pow(x % 10, 5)
    x = x / 10
  }

  if (sum == n) {
    println(n)
    total += n
  }
}


println(s"The sum of numbers ${total}")