/**
  * Problem 51. Prime digit replacements
  *
  * By replacing the 1st digit of the 2-digit number *3, it turns out that six of the nine possible values: 13, 23, 43, 53, 73, and 83, are all prime.
  *
  * By replacing the 3rd and 4th digits of 56**3 with the same digit, this 5-digit number is the first example having seven primes among the ten generated numbers, yielding the family: 56003, 56113, 56333, 56443, 56663, 56773, and 56993. Consequently 56003, being the first member of this family, is the smallest prime with this property.
  *
  * Find the smallest prime which, by replacing part of the number (not necessarily adjacent digits) with the same digit, is part of an eight prime value family.
  *
  * https://projecteuler.net/problem=51
  **/

import collection.mutable._

val max = 1000000

// size of prime number family
val primeFamilySize = 8

var primes = Array.fill[Boolean](max)(true)

// Generate prime numbers up to Max
// using the sieve of Eratosthenes
for (i <- 2 until primes.length - 1) {
  if (primes(i)) {
    var j = i.toLong * i
    while (j < max) {
      primes(j.toInt) = false
      j += i
    }
  }
}

/**
  * Generates possible patterns for number x
  * @param x Any prime number
  * @param digitGroup Internally used to keep track of the same digits
  * @return List of Patterns. Example below:
  *
  * 222 -> List(xxx, 2xx, x2x, 22x, xx2, 2x2, x22, 222)
  * 3233 -> List(x2xx, 32xx, x23x, 323x, x2x3, 32x3, 3x33, x233, 3233)
  * 1009 -> List(100x, 1xx9, 10x9, 1x09, x009, 1009)
  * 56663 -> List(5666x, 5xxx3, 56xx3, 5x6x3, 566x3, 5xx63, 56x63, 5x663, x6663, 56663)
  */
def nToPatterns(x: Int, digitGroup: Int = -1): Array[String] = {
  val digit = x % 10

  var choices = Array(digit.toString)

  // append another option "x"
  if (-1 == digitGroup || digitGroup == digit)
    choices :+= "x"

  choices.flatMap { ch=>
    if ( x > 9 ) {
      nToPatterns(x / 10, if (ch == "x")  digit else digitGroup).map(_ + ch)
    } else {
      Array(ch)
    }
  }

}

var patterns = collection.mutable.Map[String, ListBuffer[Int]]()
primes.indices.filter(primes(_)).foreach{ i=>
  nToPatterns(i).foreach{ pattern=>
    if (patterns isDefinedAt pattern) {
      patterns(pattern) += i
    } else {
      patterns(pattern) = ListBuffer(i)
    }
  }
}

// filter out for a map elements with a list size == size of prime family
patterns.retain((_, v) => v.size == primeFamilySize)

//Sort by highest number of family size, if we retain multiple family sizes
//Sort again by the pattern length
println(patterns.toSeq.sortBy(-_._2.size).sortBy(_._1.length).foreach(println(_)))

/*
(x2x3x3,ListBuffer(121313, 222323, 323333, 424343, 525353, 626363, 828383, 929393))
()

real    0m2.939s
user    0m7.454s
sys     0m0.308s


 */



