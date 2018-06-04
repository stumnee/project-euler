/**
  * Problem 52. Permuted multiples
  *
  * It can be seen that the number, 125874, and its double, 251748, contain exactly the same digits, but in a different order.
  * Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.
  *
  * https://projecteuler.net/problem=52
  **/

var num = 1L

def notSameDigits(x: Long): Boolean = {
  num.toString.toArray.sorted.mkString != x.toString.toArray.sorted.mkString
}

while (notSameDigits(num * 2) || notSameDigits(num * 4) || notSameDigits(num * 3) || notSameDigits(num * 6)) {
  num += 1
}

println(num)

/*
142857

real    0m1.307s
user    0m1.610s
sys     0m0.153s

 */