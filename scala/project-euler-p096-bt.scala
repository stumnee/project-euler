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


class Sudoku(r: Vector[Vector[Int]]) {
  val rows: Vector[Vector[Int]] = r

  def valueAt(absIdx: Int): Int = {
    if (absIdx < 81)
      rows(absIdx / 9)(absIdx % 9)
    else
      0
  }

  def valueAt(r: Int, c: Int): Int = {
    rows(r)(c)
  }

  def update(absIdx: Int, newValue: Int): Sudoku = {
    update(absIdx / 9, absIdx % 9, newValue)
  }

  /**
    * Deep copy with a new value
    * @param r
    * @param c
    * @param newValue
    * @return
    */
  def update(r: Int, c: Int, newValue: Int): Sudoku = {
    new Sudoku(rows.zipWithIndex.map{ case (row, rowIdx) =>
      if (rowIdx != r) {
        row
      } else {
        row.zipWithIndex.map { case (value, colIdx) =>
          if (rowIdx == r && colIdx == c)
            newValue
          else
            value
        }
      }
    })
  }

  def testDistinct(coll: Vector[Int]): Boolean = {
    // 10s Slower method commented out
    /*
      val nonZeroColl = coll.filter(_ > 0)
      nonZeroColl.distinct.length == nonZeroColl.length
     */

    val digits = Array(0,0,0,0,0,0,0,0,0,0)
    for (i<-coll.indices) {
      if (coll(i) > 0 && digits(coll(i)) != 0) {
        return false
      } else {
        digits(coll(i)) = 1
      }
    }
    true
  }

  def testLocalized(rowIdx: Int, colIdx: Int) = {
    (testDistinct(rows(rowIdx)) &&

    testDistinct(rows.map(_(colIdx))) &&

    testDistinct((rowIdx / 3 * 3 to rowIdx / 3 * 3 + 2).flatMap{rowIdx=>
      rows(rowIdx).slice(colIdx / 3 * 3, colIdx / 3 * 3 + 3)
      }.toVector))

  }
  def test: Boolean = {


    (rows.map (testDistinct(_)).forall(_ == true) &&

    rows.indices.map { colIdx =>
      testDistinct(rows.map(_(colIdx)))
    }.forall(_ == true) &&

    rows.grouped(3).map{rowGroup=>
      (0 to 2).map { colGroupIdx=>
        testDistinct(rowGroup.flatMap{row=>
          row.slice(colGroupIdx * 3, colGroupIdx * 3 + 3)
        })
      }.forall(_ == true)
    }.forall(_ == true))

  }

  override def toString: String = {
    rows.map { row =>
      row.map(_ + " ").mkString + "\n"
    }.mkString
  }
}

val puzzles = data.grouped(9).map { puzzle =>
  new Sudoku(puzzle.map(_.toCharArray.map(_.asDigit).toVector).toVector)
}.toArray

def solveRecursively(puzzle: Sudoku, absIdx: Int = 0): Sudoku = {
  var newAbsIdx = absIdx
  var updatedPuzzle: Sudoku = puzzle

    while (puzzle.valueAt(newAbsIdx) != 0) {
      newAbsIdx += 1
    }

    if (newAbsIdx < 81) {
      val (rowIdx, colIdx) = (newAbsIdx / 9, newAbsIdx % 9)
      val it = (1 to 9).iterator
      do {
        do {
          updatedPuzzle = puzzle.update(rowIdx, colIdx, it.next())
        } while (it.hasNext && !updatedPuzzle.testLocalized(rowIdx, colIdx))

        if (updatedPuzzle.testLocalized(rowIdx, colIdx)) {
          updatedPuzzle = solveRecursively(updatedPuzzle, newAbsIdx + 1)
        }

      } while (it.hasNext && !updatedPuzzle.test)
    }

  updatedPuzzle
}

var solved = 0
var sum = 0
for (i <- 0 to 49) {
  var solution = solveRecursively(puzzles(i))
  sum += solution.valueAt(0) * 100 + solution.valueAt(1) * 10 + solution.valueAt(2)
  solved += 1
  println(solution)
  println(s"puzzle ${i + 1} solved")
}
println(sum)
/*

24702

real    0m14.458s
user    0m17.110s
sys     0m0.302s


 */

