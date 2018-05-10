import scala.collection.mutable.ListBuffer

/**
  * Problem 26. Reciprocal cycles
  *
  * A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions with denominators 2 to 10 are given:
  * Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that 1/7 has a 6-digit recurring cycle.
  * Find the value of d &lt; 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.
  *
  * https://projecteuler.net/problem=26
  **/


def divCycle(divisor: Int): Int = {
  var x = 1
  var irrational = false
  var remainder = ListBuffer[Int]()
  do {
    x *= 10
    while (x < divisor) {
      x *= 10
      remainder += 0
    }


    x = x % divisor

    if (remainder.indexOf(x) == -1) {
      remainder += x
    } else {
      irrational = true
    }
  } while (x > 0 &&  !irrational)

  if (!irrational) {
    return 0
  }
  remainder.size - remainder.indexOf(x)
}

val max = 1000
var (recurringCycleCount, divisor) = (1, 1)

for (d <- 2 until max) {
  val currentRecurringCycleCount = divCycle(d)

  if (recurringCycleCount < currentRecurringCycleCount) {
    recurringCycleCount = currentRecurringCycleCount
    divisor = d
  }
}
println(s"${divisor} at recurringCycleCount = ${recurringCycleCount}")

/*
983 at recurringCycleCount = 982

real    0m1.731s
user    0m1.249s
sys     0m0.136s

 */