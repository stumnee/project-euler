/**
  * Problem 18. Maximum path sum I
  *
  * By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from top to bottom is 23.
  *    3
  *   7 4
  *  2 4 6
  * 8 5 9 3
  *
  * That is, 3 + 7 + 4 + 9 = 23.
  *
  * Find the maximum total from top to bottom of the triangle below:
  *                   75
  *                 95 64
  *               17 47 82
  *              18 35 87 10
  *             20 04 82 47 65
  *            19 01 23 75 03 34
  *           88 02 77 73 07 63 67
  *         99 65 04 28 06 16 70 92
  *        41 41 26 56 83 40 80 70 33
  *       41 48 72 33 47 32 37 16 94 29
  *      53 71 44 65 25 43 91 52 97 51 14
  *     70 11 33 28 77 73 17 78 39 68 17 57
  *    91 71 52 38 17 14 91 43 58 50 27 29 48
  *  63 66 04 68 89 53 67 30 73 16 69 87 40 31
  * 04 62 98 27 23 09 70 98 73 93 38 53 60 04 23
  *
  * NOTE: As there are only 16384 routes, it is possible to solve this problem by trying every route. However, Problem 67, is the same challenge with a triangle containing one-hundred rows; it cannot be solved by brute force, and requires a clever method! ;o)
  *
  * https://projecteuler.net/problem=18
  **/

class Node(var v: Int, var m: Int = 0) {
  // v: value of a triangle node
  // m: max value of a path below starting the node
  // if m == 0, node never visited
}


val triangle = "75\n95 64\n17 47 82\n18 35 87 10\n20 04 82 47 65\n19 01 23 75 03 34\n88 02 77 73 07 63 67\n99 65 04 28 06 16 70 92\n41 41 26 56 83 40 80 70 33\n41 48 72 33 47 32 37 16 94 29\n53 71 44 65 25 43 91 52 97 51 14\n70 11 33 28 77 73 17 78 39 68 17 57\n91 71 52 38 17 14 91 43 58 50 27 29 48\n63 66 04 68 89 53 67 30 73 16 69 87 40 31\n04 62 98 27 23 09 70 98 73 93 38 53 60 04 23"
    .split("\n")
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

1074

real    0m1.335s
user    0m1.063s
sys     0m0.135s

*/