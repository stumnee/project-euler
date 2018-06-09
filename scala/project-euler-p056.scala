/**
  * Problem 56. Powerful digit sum
  *
  * A googol (10100) is a massive number: one followed by one-hundred zeros; 100100 is almost unimaginably large: one followed by two-hundred zeros. Despite their size, the sum of the digits in each number is only 1.
  * Considering natural numbers of the form, ab, where a, b &lt; 100, what is the maximum digital sum?
  *
  * https://projecteuler.net/problem=56
  **/


val range = 2 until 100

println(range.map { n=>
  var x = BigInt(n)

  range.map { _=>
      x *= n
      x.toString.map(_.asDigit).sum
  }.max
}.max)


/*
972

real    0m1.262s
user    0m1.562s
sys     0m0.130s

 */