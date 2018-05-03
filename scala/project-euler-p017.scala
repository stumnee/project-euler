/**
  * Problem 17. Number letter counts
  *
  * If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.
  * 
  * If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?
  * 
  * NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) contains 23 letters and 115 (one hundred and fifteen) contains 20 letters. The use of "and" when writing out numbers is in compliance with British usage.
  *
  * https://projecteuler.net/problem=17
  **/

val words = List[String]("", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen")

def thousands(i: Int): String = {
  if (i > 999)
    s"${words(i / 1000)} thousand "
  else
    ""
}

def hundreds(i: Int): String = {
  var n = i % 1000
  if (n > 99)
    s"${words(n / 100)} hundred "
  else
    ""
}

def tens(i: Int): String = {
  var s = ""
  var n = i
  if (n > 100 && n % 100 > 0) {
    s = "and "
  }
  n = n % 100
  if (n > 19) {
    s += List("", "ten", "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty", "ninty")(n / 10) + " "
    n = n % 10
  }

  s + words(n)
}

def inWords(i: Int): String = {
  return s"${thousands(i)}${hundreds(i)}${tens(i)}"
}

var count = 0
for (i <- 1 to 1000) {
  count += inWords(i).replaceAll(" ","").length
}

println(count)
