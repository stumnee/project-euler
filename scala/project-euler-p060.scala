/**
  * Problem 60. Prime pair sets
  *
  * The primes 3, 7, 109, and 673, are quite remarkable. By taking any two primes and concatenating them in any order the result will always be prime. For example, taking 7 and 109, both 7109 and 1097 are prime. The sum of these four primes, 792, represents the lowest sum for a set of four primes with this property.
  * Find the lowest sum for a set of five primes for which any two primes concatenate to produce another prime.
  *
  * https://projecteuler.net/problem=60
  **/


// if there are no results returned, increase the max value
val max = 100000000

// number of primes in a set
val primeSet = 5

var primes = Array.fill[Boolean](max)(true)
primes(0) = false
primes(1) = false

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

var p = primes.indices.filter(idx=>primes(idx) && idx > 1)
  .filter(_ > 9)
  .flatMap{ p=>

  // Generate possible combination of prime concatenations
  // 7109 => 7 + 109
  val s = p.toString
  (1 until s.length).map {
    idx =>
      val (a, b) = (s.substring(0, idx).toInt, s.substring(idx).toInt)
      if (s.charAt(idx) != '0' && primes(a) && primes(b)) {
        (a, b)
      } else {
        null
      }
  }.filter(_ != null)
}
  // Convert List of tuples (a, b) to Map of List
  // (a1->(b1, b2...), a2->(b9, b10 ....))
  .groupBy(_._1)
  .mapValues(_.map(_._2))
  // remove map elements with primes length less than desired amount
  .filter(_._2.length >= primeSet )

var items = p.map(_._2.length).sum

println(s"Map created; size=${p.size} items=$items\n")


// remove map values don't form a prime with a map key by concatenation
p = p.transform((k,v)=>v.filter{x =>p.isDefinedAt(x) && p(x).indexOf(k) > -1}).filter(_._2.length >= primeSet)

items = p.map(_._2.length).sum

println(s"Removed non pair prime items with a map key; size=${p.size} items=$items\n")



p = p.transform((k,v)=>v.filter{x =>p.isDefinedAt(x) && p(x).indexOf(k) > -1}).filter(_._2.length >= primeSet)

items = p.map(_._2.length).sum

println(s"Removed non pair prime items with a map key; size=${p.size} items=$items\n")


//Reduce 'till no more reduction
// Make sure map values (aka peers) have prime set relations
var diff = 0
do {
  p = p.transform((k,v) => v.filter{ x=>
    // get Peer pair count
    v.map{
      n=>
        if (n != x && p.isDefinedAt(x) && p(x).indexOf(n) > -1 ) 1 else 0
    }.sum >= primeSet - 2
  }).filter(_._2.length >= primeSet - 1)

  diff = items - p.map(_._2.length).sum
  items = items - diff

  println(s"Removed non peer pair prime items; size=${p.size} items=${items}\n")

} while (diff > 0)

// Keep map elements with the size of primeSet constant
// Flatten map to a List of Vector of primes
// Sort Vector elements/primes
// Group By sum of primes

println(p.filter(_._2.size == primeSet - 1).map(m=>m._1+:m._2).map(_.sorted).groupBy(_.sum))

/*

TODO: reduce memory usage

time scala -J-Xmx1g scala/project-euler-p060.scala
Map created; size=14843 items=974972

Removed non pair prime items with a map key; size=4031 items=91266

Removed non pair prime items with a map key; size=4031 items=69632

Removed non peer pair prime items; size=858 items=8520

Removed non peer pair prime items; size=100 items=765

Removed non peer pair prime items; size=11 items=68

Removed non peer pair prime items; size=5 items=20

Removed non peer pair prime items; size=5 items=20

Map(26033 -> List(Vector(13, 5197, 5701, 6733, 8389), Vector(13, 5197, 5701, 6733, 8389), Vector(13, 5197, 5701, 6733, 8389), Vector(13, 5197, 5701, 6733, 8389), Vector(13, 5197, 5701, 6733, 8389)))

real    0m25.331s
user    0m39.925s
sys     0m0.638s

 */