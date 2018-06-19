/**
  * Problem 62. Cubic permutations
  *
  * The cube, 41063625 (345^3), can be permuted to produce two other cubes: 56623104 (384^3) and 66430125 (4053).
  * In fact, 41063625 is the smallest cube which has exactly three permutations of its digits which are also cube.
  *
  * Find the smallest cube for which exactly five permutations of its digits are cube.
  *
  * https://projecteuler.net/problem=62
  **/


val permutations = 5
val range = 1 to 10000 //increase as needed

/**
  * 1. Generate a sequence of tuple ( cube value, cube's digits sorted) for 1 to n
  *     n = 8; cube = 8 * 8 * 8 = 512; tuple = (125, 512)
  * 2. Group by cube's digits sorted
  * 3. Convert it to a map with values of cube digits
  * 4. Filter and keep map keys with only values count equals to the permutations count
  * 5. Sort by the map values
  * 6. Print the min
  */
try {
  val min = range.map { x=>
      val cube = x.toLong * x * x
      (cube.toString.sorted, cube)
    }
    .groupBy(_._1)
    .mapValues(_.map(_._2))
    .filter(_._2.length == permutations)
    .flatMap(_._2).min

  println(min)

} catch {
  case e: Exception => println("No results, increase the range")
}

/*

127035954683

real    0m1.277s
user    0m1.661s
sys     0m0.135s


 */