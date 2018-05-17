/**
  * Problem 35. Circular primes
  *
  * The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719, are themselves prime.
  *
  * There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.
  *
  * How many circular primes are there below one million?
  *
  * https://projecteuler.net/problem=35
  **/

import scala.collection.mutable.ListBuffer

val max = 1000000

// Generate prime numbers
// 78498 primes @ max=1M

var primes = ListBuffer[Int](2)
var map = collection.mutable.Map[Int, Int](2->1)


for (i <- 3 until max by 2) {
  if (primes.forall(prime=> i % prime != 0)) {

    primes += i

    // put only prime numbers consist of odd numbers not including 5
    if (i.toString.matches("[1379]+")) {
      map += i -> 1
    }
  }
}

var count = 0

map.foreach { case(prime, _) =>
  var x = prime.toString

  if ((1 to x.length).map(i=>
    // rotate all the digits
    (x.takeRight(i) + x).substring(0, x.length).toInt
  ).forall(p=> map isDefinedAt p)) {
    // increment the count, if prime number is found in the map
    count += 1
    println(x)
  }
}

println(s"count = ${count}")

/*
993319
933199
131
7793
373
113
331999
17
99371
93719
71
11
37199
39119
3779
2
971
1931
5
337
199933
19937
199
393919
719
73
9377
919393
13
939391
31
391939
991
97
7
79
9311
37
193939
939193
319993
919
11939
733
93911
19391
3
91193
999331
71993
7937
311
3119
1193
197
count = 55

real    0m25.459s
user    0m25.764s
sys     0m0.209s


 */
