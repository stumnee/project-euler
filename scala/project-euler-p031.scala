/**
  * Problem 31. Coin sums
  *
  * In England the currency is made up of pound, £, and pence, p, and there are eight coins in general circulation:
  *   1p, 2p, 5p, 10p, 20p, 50p £1 (100p), £2 (200p)
  *
  * It is possible to make £2 in the following way:
  *
  *   1x£1 + 1x50p + 2x20p + 1x5p + 1x2p + 3x1p
  *
  * How many different ways can £2 be made using any number of coins?
  *
  * https://projecteuler.net/problem=31
  **/

/*
  +- (1) <-- 200p <--
  +- (1) <-- 100p <-- (1) <-- 100p
                  <-- (1) <-- 50p <-- (1) <-- 50p

 */
val coins = Array[Int](1, 2, 5, 10, 20, 50, 100, 200)
val pence = 200

/**
  *
  * @param value Amount in pence
  * @param minCoin Optional param to keeps next coins are in order
  * @return A number of combinations for the given value
  */
def solutions(value: Int, minCoin: Int = 0): Int = {
  coins.map(c=>
    if (value < c || minCoin > c) {
      0
    } else if (value == c) {
      1
    } else {
      solutions(value - c, c)
    }
  ).sum
}

println(solutions(pence))

/**
73682

real    0m1.869s
user    0m1.756s
sys     0m0.192s
*/
