/**
  * Problem 34. Digit factorials
  *
  * 145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.
  * Find the sum of all numbers which are equal to the sum of the factorial of their digits.
  * Note: as 1! = 1 and 2! = 2 are not sums they are not included.
  *
  * https://projecteuler.net/problem=34
  **/

// 9! = 362880
// (7 digit number max value) 9!+9!+9!+9!+9!+9!+9!+9! = 2540160 (7 digits)
// (8 digit number max value) 9!+9!+9!+9!+9!+9!+9!+9! = 2903040 (7 digits) <-- not possible
// any factorial sum greater than 2540160 requires 8 or more digits

val max = 2540160

val factorials = (0 to 9).map{x=>
  var i=1
  (1 to x).foreach { j=>i*=j }
  (x, i)
}.toMap

var total = 0

for (n <- 3 until max ) {
  var sum = 0
  var x = n
  while (x > 0) {
    sum += factorials(x % 10)
    x = x / 10
  }

  if (sum == n) {
    println(n)
    total += n
  }
}

println(s"sum = ${total}")

/*

145
40585
sum = 40730

real    0m1.492s
user    0m1.416s
sys     0m0.134s

 */
