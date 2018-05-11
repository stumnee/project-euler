/**
  * Problem 28. Number spiral diagonals
  *
  * Starting with the number 1 and moving to the right in a clockwise direction a 5 by 5 spiral is formed as follows:
  *
  * 21 22 23 24 25
  * 20  7  8  9 10
  * 19  6  1  2 11
  * 18  5  4  3 12
  * 17 16 15 14 13
  *
  * It can be verified that the sum of the numbers on the diagonals is 101.
  * What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral formed in the same way?
  *
  * https://projecteuler.net/problem=28
  **/

/**
  *
  * @param size A size of a square
  * @return Sum of corners
  */
def cornerSum(size: Int): Int = {
  // start with a right top corner
  var corner = size * size

  var sum = corner

  if (size > 1) {
    for (i <- 1 to 3) {
      // each corner number is (size - 1) less going counter clockwise
      corner -= size - 1
      sum += corner
    }
  }
  sum
}

val max = 1001
var sum = 0

// square grows by 2
for (i <- 1 to max by 2) {
  sum += cornerSum(i)
}

println(sum)
/*
669171001

real    0m1.173s
user    0m1.053s
sys     0m0.133s

 */