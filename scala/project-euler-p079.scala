/**
  * Problem 79. Passcode derivation
  *
  * A common security method used for online banking is to ask the user for three random characters from a passcode. For example, if the passcode was 531278, they may ask for the 2nd, 3rd, and 5th characters; the expected reply would be: 317.
  * The text file, keylog.txt, contains fifty successful login attempts.
  * Given that the three characters are always asked for in order, analyse the file so as to determine the shortest possible secret passcode of unknown length.
  *
  * https://projecteuler.net/problem=79
  **/

val passcode = io.Source.fromFile("p079_keylog.txt").mkString.split("\n")

passcode.foreach(x=>println(x))

def isValid(x: Long): Boolean = {
  passcode.forall{ code =>
    val s = x.toString
    code.forall{ ch=>
      s.indexOf(ch) > -1
    }
  }
}

var i = 1000L

while (!isValid(i)) {
  i += 1
}

println(i)