/**
  * Problem 67. Maximum path sum II
  *
  * By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from top to bottom is 23.
  *
  *    37 4
  *   2 4 6
  *  8 5 9 3
  *
  * That is, 3 + 7 + 4 + 9 = 23.
  *
  * Find the maximum total from top to bottom in triangle.txt (right click and 'Save Link/Target As...'), a 15K text file containing a triangle with one-hundred rows.
  *
  * NOTE: This is a much more difficult version of Problem 18. It is not possible to try every route to solve this problem, as there are 299 altogether! If you could check one trillion (1012) routes every second it would take over twenty billion years to check them all. There is an efficient algorithm to solve it. ;o)
  *
  * https://projecteuler.net/problem=67
  **/

import scala.io.Source

class Node(var v: Int, var m: Int = 0) {
  // v: value of a triangle node
  // m: max value of a path below starting the node
  // if m == 0, node never visited
}

val filename = "p067_triangle.txt"

var triangle = Source.fromFile(filename)
  .getLines()
  .toArray
  .map(_.split(" ")
  .map(x=>new Node(x.toInt, 0)))



/**
  * Triangle addressing scheme
  *           0
  *        -1   +1
  *      -2   0   +2
  *   -3   -1   +1  +3
  *
  * To convert to Bidimensional Array Indexes
  *           0
  *         0   1
  *       0   1   2
  *     0   1   2   3
  *
  * @param x -left bound ... 0 ... +right bound
  * @param y Row index 0 until array.length
  * return max value starting @ current node at x,y
  */
def paths(x: Int, y: Int): Int = {
  val row = triangle(y)
  var idx = (x - 1 + row.length % 2 + row.length)/2

  if (idx < 0 || idx >= row.length) {
    // invalid index,
    // no max value
    return 0
  }

  val node: Node = row(idx)

  if (node.m > 0) {
    // already calculated,
    // so return the max value
    return node.m
  }

  node.m = node.v

  if (y < triangle.length - 1) {
    // get max path from left below path
    var a = paths(x - 1, y + 1)
    // get max path from right below path
    var b = paths(x + 1, y + 1)

    // increment max sum of path with best possible route max
    node.m +=  (if (a > b) a else b)
  }

  node.m
}


println(paths(0,0))

/**

7273

real    0m1.335s
user    0m1.063s
sys     0m0.135s

  */