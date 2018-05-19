/**
  * Problem 38. Pandigital multiples
  *
  * Take the number 192 and multiply it by each of 1, 2, and 3:
  * By concatenating each product we get the 1 to 9 pandigital, 192384576. We will call 192384576 the concatenated product of 192 and (1,2,3)
  * The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4, and 5, giving the pandigital, 918273645, which is the concatenated product of 9 and (1,2,3,4,5).
  * What is the largest 1 to 9 pandigital 9-digit number that can be formed as the concatenated product of an integer with (1,2, ... , n) where n &gt; 1?
  *
  * https://projecteuler.net/problem=38
  **/


// given example 918273645 that is not the largest
// num x 1 > 92....
// 92 <= num <= 9876 // digits can not be repeated

val start = 1  // it can start with 92 but execution time saved was negligible
val end = 9876 // n > 1, concatenation of num x 1 + num x 2 < 987654321, num has to have max 4 digits non repeating
var largest = 0 // initial number can be 918273645 which is taken from the example

def isPandigital(s: String): Boolean = {
  for (i <- 0 until s.length - 1) {
    if (s.indexOf(s(i), i + 1) > -1) {
      return false
    }
  }
  true
}

for (num <- start to end) {
  var s = num.toString

  var n = 2
  while (s.length < 9) {
    s += num * n
    n += 1
  }

  if (s.length == 9 && s.indexOf('0') == -1 && isPandigital(s)) {
    if (s.toInt > largest) {
      largest = s.toInt
    }
  }
}

println(largest)
/*
932718654

real    0m1.362s
user    0m1.045s
sys     0m0.111s

 */