/**
  * Problem 43. Sub-string divisibility
  *
  * The number, 1406357289, is a 0 to 9 pandigital number because it is made up of each of the digits 0 to 9 in some order, but it also has a rather interesting sub-string divisibility property.
  * Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note the following:
  * Find the sum of all 0 to 9 pandigital numbers with this property.
  *
  * https://projecteuler.net/problem=43
  **/

// factors required by index; -1 @ idx=0 is ignored
val factors = Array(-1, 2, 3, 5, 7, 11, 13, 17)

/**
  * Converts Array of Int to Long
  * @param idx First Array Element to form a number
  * @param len How many digits?
  * @param arr Source Array
  * @return Resulting number
  */
def arrayToNum(idx: Int, len: Int, arr: Array[Int]): Long = {
  var num = 0L

  for (i <- idx until idx + len) {
    num = num * 10 + arr(i)
  }
  num
}

/**
  * Generates pan digital numbers recursively
  * @param digits Array contains a generated number and it passed to next level
  * @param start Optional integer number used to force first digit to be 1 not 0
  * @return Sum of pandigital numbers conforms the sub-divisibility property
  */
def genNumber(digits: Array[Int], start: Int = 0): Long = {
  var sum = 0L
  for (elem <- 0 to 9) {
    if (elem >= start && digits.indexOf(elem) == -1 ) {
      var panDigital = digits :+ elem

      // 10 digit pandigital number complete?
      if (panDigital.length == 10) {

        // Test subdivisibity, start from the last 3 digits
        var idx = factors.length - 1

        // as long as divisibility works, keep testing previous 3 digits groups
        while (idx > 0 && arrayToNum(idx, 3, panDigital) % factors(idx) == 0) {
          idx -= 1
        }

        // if index reaches the first digit, every sub group has the required sub-divisibility property
        if (idx == 0) {
          sum += arrayToNum(idx, 10, panDigital)
          println(panDigital.mkString)
        }
      } else {
        // next digits of the pan digital number
        sum += genNumber(panDigital)
      }

    }
  }
  sum
}


println("Sum=" + genNumber(Array(), 1))

/*
1406357289
1430952867
1460357289
4106357289
4130952867
4160357289
Sum=16695334890

real    0m5.366s
user    0m6.330s
sys     0m0.181s



 */



