/**
  * Problem 49. Prime permutations
  *
  * The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330, is unusual in two ways: (i) each of the three terms are prime, and, (ii) each of the 4-digit numbers are permutations of one another.
  *
  * There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, exhibiting this property, but there is one other 4-digit increasing sequence.
  *
  * What 12-digit number do you form by concatenating the three terms in this sequence?
  *
  * https://projecteuler.net/problem=49
  **/


val MAX = 9999

// assume all the numbers are prime before ruling them out
var primes = Array.fill[Boolean](MAX)(true)

// Generate prime numbers up to 9999
// using the sieve of Eratosthenes
for (i <- 2 until primes.length - 1) {
  if (primes(i)) {
    var j = i * i
    while (j < MAX) {
      primes(j) = false
      j += i
    }
  }
}

/**
  * Generates array of int permutations for the digits
  * NOTE: digits.indices is necessary when there are duplicates in digits
  * @param digits Array of digits of an original number
  * @param num Internal storage used by recursion
  * @return Array of Int results
  */
def perm(digits: Array[Int], num: Array[Int] = Array()): Array[Int] = {
  if (num.length < digits.length) {
    digits.indices.filter(num.indexOf(_) == -1).flatMap(d=> perm(digits, num :+ d)).toArray
  } else {
    Array(num.foldLeft(0)((b,a) => b*10 + digits(a)))
  }
}

var n = 1001

do {
  if (primes(n)) {

    // disect digits
    val digits = n.toString.map(_ - '0').toArray

    // get permutations based on digits
    // leave distinct and four digits and lastly prime numbers only
    val nums = perm(digits)
      .distinct
      .filter(_ > 999)
      .filter(primes(_))


    // must have at least 3 permutations
    if (nums.length > 2) {
      // Storage for differences Map
      // Diff-> List of |a-b|
      var diffs = collection.mutable.Map[Int, List[(Int, Int)]]()

      // get all the number diffs
      for (i <- 0 until nums.length) {
        for (j <- i + 1 until nums.length) {
          val diff = math.abs(nums(i) - nums(j))
          var occurrences = List[(Int, Int)]()
          if (diffs isDefinedAt diff) {
            occurrences = diffs(diff)
          }
          diffs += (diff->((nums(i), nums(j)) :: occurrences))
        }
      }

      // Retain the diff map with more than one list entries (combinations)
      diffs.retain((k,v) => v.length > 1)


      diffs.foreach { case (k, list) =>
        // ll - flat list with distinct integers
        // list - original list of tuples (a, b)
        val ll = list.flatMap(t => List(t._1, t._2)).distinct

        if (ll.size < list.size * 2) {
          // found something??
          println(s"diff=${k} ${list.flatMap(t => List(t._1, t._2)).distinct.sorted.mkString}")
        }
      }

      // no need to revisit the same numbers
      // just mark 'em non-prime, so they will be ignored
      nums.foreach(primes(_) = false)
    }
  }

  n += 2
} while (n < MAX)


/*
diff=3330 148748178147
diff=3330 296962999629

real    0m1.463s
user    0m1.537s
sys     0m0.128s

 */