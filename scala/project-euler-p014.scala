/**
  * Problem 14. Longest Collatz sequence
  *
  * The following iterative sequence is defined for the set of positive integers:
  *
  * n → n/2 (n is even)
  * n → 3n + 1 (n is odd)
  *
  * Using the rule above and starting with 13, we generate the following sequence:
  *
  * 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
  * It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.
  *
  * Which starting number, under one million, produces the longest chain?
  * NOTE: Once the chain starts the terms are allowed to go above one million.
  *
  * https://projecteuler.net/problem=14
  */

val max = 1000000
// Recursive, but runs into stackoverflow error
// To set stack size, scala -J-Xss200m
def collatzRecursive(n: Long): List[Long] = {
  if (n == 1) {
    return List(1)
  }

  n :: collatz(if (n % 2 == 0) n /2 else 3 * n + 1)
}

def collatz(i: Long): List[Long] = {
  var seq = List[Long](i)
  var n = i
  while (n > 1) {
    n = if (n % 2 == 0) n /2 else 3 * n + 1
    seq = n :: seq
  }
  seq
}

var current = List[Long]()

for (x <- 1 until max) {
  var c = collatz(x)
  if (c.size > current.size) {
    current = c
  }
}

// 837799 at length 525
println(s"${current} at length ${current.size}")
