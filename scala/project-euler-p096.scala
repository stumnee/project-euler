/**
  * Problem 96. Su Doku
  *
  * Su Doku (Japanese meaning number place) is the name given to a popular puzzle concept. Its origin is unclear, but credit must be attributed to Leonhard Euler who invented a similar, and much more difficult, puzzle idea called Latin Squares. The objective of Su Doku puzzles, however, is to replace the blanks (or zeros) in a 9 by 9 grid in such that each row, column, and 3 by 3 box contains each of the digits 1 to 9. Below is an example of a typical starting puzzle grid and its solution grid.
  * A well constructed Su Doku puzzle has a unique solution and can be solved by logic, although it may be necessary to employ "guess and test" methods in order to eliminate options (there is much contested opinion over this). The complexity of the search determines the difficulty of the puzzle; the example above is considered easy because it can be solved by straight forward direct deduction.
  * The 6K text file, sudoku.txt (right click and 'Save Link/Target As...'), contains fifty different Su Doku puzzles ranging in difficulty, but all with unique solutions (the first puzzle in the file is the example above).
  * By solving all fifty puzzles find the sum of the 3-digit numbers found in the top left corner of each solution grid; for example, 483 is the 3-digit number found in the top left corner of the solution grid above.
  *
  * https://projecteuler.net/problem=96
  **/


val data = io.Source.fromFile("p096_sudoku.txt").mkString.split("\n").filter(!_.contains("Grid"))

def allCombinations(v: Vector[Vector[Int]]): IndexedSeq[Vector[Vector[Int]]] = {
  v.indices.flatMap { idx =>
    if (v.length == idx + 1) {
      IndexedSeq(Vector(v(idx)))
    } else {
      allCombinations(v.slice(idx + 1, v.length)).map { sV=>
        if (sV == null)
          Vector(v(idx))
        else
          v(idx) +: sV
      }
    }
  }
}

def filterByMultiples(cell: Vector[Int], values: Array[Vector[Int]]): Vector[Int] = {
  val res = allCombinations(values.filter(_.length > 1).toVector).filter{ v=>
    v.size == v.flatten.distinct.size && v.size < values.length
  }.groupBy(_.flatten.distinct).mapValues(_(0))

  val len = cell.length
  var newCell = cell
  res.foreach { dd =>
    if (newCell.length == len && len > 1 && dd._2.indexOf(cell) == -1) {
      newCell = cell.filter{ digit => dd._1.indexOf(digit) == -1}
    }
  }
  newCell
}

def filterBySingleValue(cell: Vector[Int], values: Array[Vector[Int]]): Vector[Int] = {
  var newCell = cell
  if (cell.length > 1) {
    // by row
    newCell = cell.filter { digit =>
      values.map(d =>
        if (d.length == 1) d.head else 0
      ).indexOf(digit) == -1
    }
  }
  newCell
}

def boxValues(grid: Array[Array[Vector[Int]]], y: Int, x: Int): Array[Vector[Int]] = {
  (y / 3 * 3 until y / 3 * 3 + 3).flatMap { boxY =>
    (x / 3 * 3 until x / 3 * 3 + 3).map { boxX =>
      grid(boxY)(boxX)
    }
  }.toArray
}
def solve(grid: Array[Array[Vector[Int]]]) = {

  var changes = 1
  while (changes > 0) {

    changes = 0
    for (y <- 0 until 9) {
      for (x <- 0 until 9) {
        if (grid(y)(x).length > 1) {
          val len = grid(y)(x).length
          // Row
          grid(y)(x) = filterBySingleValue(grid(y)(x), grid(y))

          // Column
          grid(y)(x) = filterBySingleValue(grid(y)(x), grid.map(_(x)))

          // Box 3x3
          grid(y)(x) = filterBySingleValue(grid(y)(x), boxValues(grid, y, x))

          // Row Multiple values
          grid(y)(x) = filterByMultiples(grid(y)(x), grid(y))

          // Column Multiple values
          grid(y)(x) = filterByMultiples(grid(y)(x), grid.map(_(x)))

          // Box 3x3 Multiple values
          grid(y)(x) = filterByMultiples(grid(y)(x), boxValues(grid, y, x))


          if (len != grid(y)(x).length) {
            changes += 1
          }
        }


      }
    }
//    grid.foreach(y=>println(y.mkString))
//    println(s"changes $changes\n")
  }
  grid
}
def isErroneous(grid: Array[Array[Vector[Int]]]): Boolean = {
  grid.flatten.count(_.isEmpty) > 0
}
def isIncomplete(grid: Array[Array[Vector[Int]]]): Boolean = {
  grid.flatten.count(_.length > 1) > 0
}
def testAndSolve(grid: Array[Array[Vector[Int]]]): Array[Array[Vector[Int]]] = {
  var results = grid



  if (!isErroneous(grid) && isIncomplete(grid)) {

    val incompleteCells = (0 until 9).flatMap { boxY =>
      (0 until 9).map { boxX =>
        (boxY, boxX) -> grid(boxY)(boxX)
      }
    }.filter(e => e._2.length > 1)

    if (incompleteCells.nonEmpty) {
      val ((y: Int, x: Int), possibleValues: Vector[Int]) = incompleteCells.minBy(_._2.length)

      val it = possibleValues.iterator

      var testResults: Array[Array[Vector[Int]]] = null
      do {

        // deep copy
        testResults = results.map(_.map(cell=>cell))

        testResults(y)(x) = Vector(it.next())

        testResults = testAndSolve(solve(testResults))

      } while (it.hasNext && isErroneous(testResults))

      results = testResults
    }
  }
  // Complete
  results
}

var count = 0
var sum = 0

val grids = data.grouped(9).map { grid =>
  grid.map(_.toCharArray.map(_.asDigit).map { x =>
    if (x == 0)
      (1 to 9).toVector
    else
      Vector(x)
  })
}
grids.foreach{ grid=>
  val results = testAndSolve(solve(grid))

  println(s"Sudoku ${count + 1}")
  if (isIncomplete(results)) {
    println("Incomplete")
    results.foreach { row=>
      println(row.mkString)
    }
  } else {
    results.foreach { row=>
      println(row.map(_(0) + " ").mkString)
    }

    val check = results.map{ row=>
      row.map(_(0)).distinct.length
    }.sum

    if (check == 81) {
      println("Verified")
    }

    println(results.head(0).head * 100 + results.head(1).head * 10 + results.head(2).head)

    sum += results.head(0).head * 100 + results.head(1).head * 10 + results.head(2).head
  }
  println("\n\n")
  count += 1
}

println(s"Sum = $sum")



