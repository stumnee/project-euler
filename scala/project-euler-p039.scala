/**
  * Problem 39. Integer right triangles
  *
  * If p is the perimeter of a right angle triangle with integral length sides, {a,b,c},
  * there are exactly three solutions for p = 120.
  *
  * {20,48,52}, {24,45,51}, {30,40,50}
  *
  * For which value of p ≤ 1000, is the number of solutions maximised?
  *
  * https://projecteuler.net/problem=39
  **/

import scala.collection.mutable._

val maxP = 1000

var map = Map[Int, ListBuffer[(Int, Int, Int)]]()

for (a <- 1 to maxP - 3) {
  for (b <- a to (maxP - a)/2) {
    val hypotenuse = math.sqrt(a*a + b*b)

    val c = hypotenuse.toInt

    val p = a + b + c

    if (p <= maxP && p == a + b + hypotenuse) {
      if (!(map isDefinedAt p)) {
        map(p) = ListBuffer[(Int, Int, Int)]()
      }

      map(p) += ((a, b, c))
    }
  }
}

val solutions = map.reduceLeft((x, y) => if (x._2.size > y._2.size) x else y)

println(s"p=${solutions._1} with ${solutions._2.size} solutions\n")

solutions._2.foreach(tuple => println(s"a=${tuple._1}, b=${tuple._2}, c=${tuple._3}"))

/*

p=840 with 8 solutions

a=40, b=399, c=401
a=56, b=390, c=394
a=105, b=360, c=375
a=120, b=350, c=370
a=140, b=336, c=364
a=168, b=315, c=357
a=210, b=280, c=350
a=240, b=252, c=348

real    0m1.146s
user    0m1.106s
sys     0m0.118s

 */