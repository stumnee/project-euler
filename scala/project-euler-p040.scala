/**
  * Problem 40. Champernowne's constant
  *
  * An irrational decimal fraction is created by concatenating the positive integers:
  * 0.123456789101112131415161718192021...
  * It can be seen that the 12th digit of the fractional part is 1.
  * If dn represents the nth digit of the fractional part, find the value of the following expression.
  * d1 × d10 × d100 × d1000 × d10000 × d100000 × d1000000
  *
  * https://projecteuler.net/problem=40
  **/


var s = new StringBuilder()

val max = 1000000

var i = 1
while (s.length <= max) {
  s.append(i)
  i += 1
}


var product = 1
var n = 1

while (n < max) {
  n *= 10
  product *= (s.charAt(n - 1) - '0')
}


println(product)

/*

210

real    0m1.192s
user    0m1.108s
sys     0m0.119s


 */