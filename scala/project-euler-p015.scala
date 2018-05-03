/**
  * Problem 15. Lattice paths
  *
  * Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down, there are exactly 6 routes to the bottom right corner.
  * How many such routes are there through a 20×20 grid?
  *
  * https://projecteuler.net/problem=15
 **/

val (maxX, maxY) = (20, 20)

def latticePaths(topX: Int, topY: Int): Long = {
  //println(topX, topY)

  var total = 0L

  if (topX == maxX && topY == maxY) {
    return 1
  }

  //move to right
  if (topX < maxX) {
    total += latticePaths(topX + 1, topY)
  }


  //move down
  if (topY < maxY) {
    total += latticePaths(topX, topY + 1)
  }

  total
}

println(latticePaths(0,0))

/*

TODO: too slow

137846528820

real    23m53.895s
user    23m37.159s
sys     0m7.406s
*/