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

def isValid(s: String): Boolean = {
  passcode.forall { code =>
    var lastIdx = 0
    code.forall { ch=>
      lastIdx = s.indexOf(ch, lastIdx)
      lastIdx > -1
    }
  }
}

var i = 1L

while (!isValid(i.toString)) {
  i += 1
}

println(i)

/*
73162890

real    0m5.775s
user    0m5.781s
sys     0m0.190s

 */