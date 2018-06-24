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

val allDigits = 1 to 9

def comb(v: Vector[Vector[Int]]): IndexedSeq[Vector[Vector[Int]]] = {
  if (v.length < 2) {
    return IndexedSeq(v)
  }
  (0 until v.length - 1).flatMap { idx =>
    (comb(v.slice(idx + 1, v.length)) :+ null).map { sV=>
      if (sV == null)
        Vector(v(idx))
      else
        v(idx) +: sV
    }
  }
}

def solve(grid: Array[Array[Vector[Int]]]) = {

  var changes = 1
  while (changes > 0) {

    changes = 0
    for (y <- 0 until 9) {
      for (x <- 0 until 9) {
        val cell = grid(y)(x)
        val oldLength = cell.length

        if (grid(y)(x).length > 1) {
          // by row
          grid(y)(x) = grid(y)(x).filter { digit =>
            grid(y).map(d =>
              if (d.length == 1) d.head else 0
            ).indexOf(digit) == -1
          }
        }

        if (grid(y)(x).length > 1) {
          // by column
          grid(y)(x) = grid(y)(x).filter { digit =>
            grid.map(_ (x)).map(d =>
              if (d.length == 1) d.head else 0
            ).indexOf(digit) == -1
          }
        }


        if (grid(y)(x).length > 1) {
          // by 3x3 box
          grid(y)(x) = grid(y)(x).filter { digit =>
            (y / 3 * 3 until y / 3 * 3 + 3).flatMap { boxY =>
              (x / 3 * 3 until x / 3 * 3 + 3).map { boxX =>
                if (grid(boxY)(boxX).length == 1) grid(boxY)(boxX).head else 0
              }
            }.indexOf(digit) == -1
          }
        }

        if (grid(y)(x).length > 1) {
          // by 3x3 box :: multiple numbers check
          /*
            using 58 occurred twice,
            so no more 5 or 8

            58   7  9         58   7   9
             4  58  2   =>     4   58  2
            581  3  6          1   3   6
            */
//          val dupes = (y / 3 * 3 until y / 3 * 3 + 3).flatMap { boxY =>
//            (x / 3 * 3 until x / 3 * 3 + 3).map { boxX =>
//              grid(boxY)(boxX).sorted
//            }
//          }.filter(_.length > 1).groupBy(i=>i).mapValues(_.size).filter(e=>e._1.size == e._2).keys
//
//
//          dupes.foreach{ dd=>
//            if (grid(y)(x) != dd) { // no exact match
//              grid(y)(x) = grid(y)(x).filter{ digit => dd.indexOf(digit) == -1}
//            }
//
//          }

          val boxVals = (y / 3 * 3 until y / 3 * 3 + 3).flatMap { boxY =>
                        (x / 3 * 3 until x / 3 * 3 + 3).map { boxX =>
                          grid(boxY)(boxX).sorted
                        }
                      }.filter(_.length > 1)


          val res = comb(boxVals.toVector).filter{v=>
            v.size == v.flatten.distinct.size && v.size < boxVals.length
          }.groupBy(_.flatten.distinct).mapValues(_(0))

          res.foreach { dd=>
            if (grid(y)(x).length > 1 && dd._2.indexOf(grid(y)(x)) == -1) {
              grid(y)(x) = grid(y)(x).filter{ digit => dd._1.indexOf(digit) == -1}
            }
          }
        }


          // by row  :: multiple numbers check
          /*
            using 58 occurred twice,
            so no more 5 or 8

            58  2  58  4  598  1  3   =>  58  2  58  4  9  1  3
            */

          val rowVals = grid(y).filter(_.length > 1)

          val res = comb(rowVals.toVector).filter{v=>
            v.size == v.flatten.distinct.size && v.size < rowVals.length
          }.groupBy(_.flatten.distinct).mapValues(_(0))

        if (y==4)  {
          println("=======", res, rowVals.mkString, "===", comb(rowVals.toVector).indexOf(Vector(4,7)))
          println()
          comb(rowVals.toVector).foreach{ff=>
            println(ff)
          }
          println()
        }
          res.foreach { dd=>
            if (grid(y)(x).length > 1 && dd._2.indexOf(grid(y)(x)) == -1) {
              grid(y)(x) = grid(y)(x).filter{ digit => dd._1.indexOf(digit) == -1}
            }
          }

//          val dupes = grid(y).filter(_.length > 1).groupBy(i=>i).mapValues(_.length).filter(e=>e._1.size == e._2).keys
//
//
//          dupes.foreach{ dd=>
//            if (grid(y)(x) != dd) { // no exact match
//              grid(y)(x) = grid(y)(x).filter{ digit => dd.indexOf(digit) == -1}
//            }
//
//          }

        if (grid(y)(x).length > 1) {

          val colVals = grid.map(_ (x)).filter(_.length > 1)

          val res = comb(colVals.toVector).filter{v=>
            v.size == v.flatten.distinct.size && v.size < colVals.length
          }.groupBy(_.flatten.distinct).mapValues(_(0))

          //println(x + "====" + res)
          res.foreach { dd=>
            if (grid(y)(x).length > 1 && dd._2.indexOf(grid(y)(x)) == -1) {
              grid(y)(x) = grid(y)(x).filter{ digit => dd._1.indexOf(digit) == -1}
            }
          }

        }


        if (oldLength != grid(y)(x).length) {
          changes += 1
        }
      }
    }
    grid.foreach(y=>println(y.mkString))
    println(s"changes $changes\n")
  }
  grid
}

var count = 0
data.grouped(9).foreach { grid=>
  if (count == 5) {

    val results = solve(grid.map(_.toCharArray.map(_.asDigit).map { x =>
      if (x == 0)
        allDigits.toVector
      else
        Vector(x)
    }))
  }
  count += 1

}
