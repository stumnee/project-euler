/**
  * Problem 37. Truncatable primes
  *
  * The number 3797 has an interesting property. Being prime itself, it is possible to continuously remove digits from left to right, and remain prime at each stage: 3797, 797, 97, and 7. Similarly we can work from right to left: 3797, 379, 37, and 3.
  * Find the sum of the only eleven primes that are both truncatable from left to right and right to left.
  * NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
  *
  * https://projecteuler.net/problem=37
  **/

val max = 11

// 2 is omitted for better performance
var primes = collection.mutable.Map[Int, Int](2->1)

var count = 0
var current = 3
var sum = 0

while ( count < max ) {
  if (primes.forall(t => current % t._1 != 0)) {

    primes += (current->1)

    var truncatable = true
    var n = current
    while (n > 10 && truncatable) {

      // remove last digit
      n = n / 10
      if (!(primes isDefinedAt n)) {
        truncatable = false
      }
    }

    n = current
    while (n > 10 && truncatable) {

      n = n % math.pow(10,math.log10(n).toInt).toInt
      if (!(primes isDefinedAt n)) {
        truncatable = false
      }
    }
    if (truncatable && current > 10) {
      println(current)
      count += 1
      sum += current
    }


  }
  //skip even numbers
  current += 2
}


println(s"Sum = ${sum}")

/*

TODO: takes too long!!!

23
37
53
73
313
317
373
797
3137
3797
739397
Sum = 748317

real    2m15.500s
user    2m18.512s
sys     0m1.004s

 */


