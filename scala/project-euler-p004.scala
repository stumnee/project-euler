/**
  * Problem 4. Largest palindrome product
  *
  * A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
  *
  * Find the largest palindrome made from the product of two 3-digit numbers.
  *
  * https://projecteuler.net/problem=4
  */

def isPalindrom(x: Int): Boolean = {
  val s = x.toString()
  s.reverse == s
}

var largest = 0

for (i <- 999 to 100 by -1) {
  for (j <- 999 to 100 by -1) {
    val product = i * j
    if (isPalindrom(product) && product > largest) {
      largest = product
    }
  }
}

println(largest)