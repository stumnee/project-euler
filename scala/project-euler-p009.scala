/**
  * Project 9. Special Pythagorean triplet
  *
  * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
  *
  * a2 + b2 = c2
  *
  * For example, 32 + 42 = 9 + 16 = 25 = 52.
  *
  * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
  *
  * Find the product abc.
  *
  * https://projecteuler.net/problem=9
  *
  */

val total = 1000

var a = 1 // .. 498
var b = 2 // up to 499

def c(): Int = {
  total - a - b
}

/*
  increment b until b is no longer less than c
  increment a if b is no longer greater than c
*/

while (a * a + b * b != c * c) {
  b = b + 1
  if (b >= c) {
    a = a + 1
    b = a + 1
  }
}

println(a * b * c)