/**
  * Problem 24. Lexicographic permutations
  *
  * A permutation is an ordered arrangement of objects. For example, 3124 is one possible permutation of the digits 1, 2, 3 and 4. If all of the permutations are listed numerically or alphabetically, we call it lexicographic order. The lexicographic permutations of 0, 1 and 2 are:
  * 012   021   102   120   201   210
  * What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
  *
  * https://projecteuler.net/problem=24
  **/

import collection.mutable.ArrayBuffer

val max = 1000000

var (s, count) = ("", 0)

/**
  * Recursively generate permutations for a subset input
  * @param a (1,2,3...)
  */
def perm(a: ArrayBuffer[Int]):Unit = {
  var (idx, leadDigit) = (0, a(0))

  if (a.length == 1) {
    count += 1
  } else {
    while (idx < a.size && count < max) {
      var b = a.clone()
      leadDigit = b.remove(idx)

      perm(b)
      idx += 1
    }

  }

  if (count == max) {
    s = leadDigit + s
  }

}

perm(ArrayBuffer(0,1,2,3,4,5,6,7,8,9))

println(s)
/*
2783915460

real    0m1.331s
user    0m1.340s
sys     0m0.152s

 */
