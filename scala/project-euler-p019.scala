/**
  * Problem 19. Counting Sundays
  *
  * You are given the following information, but you may prefer to do some research for yourself.
  * How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
  *
  * o 1 Jan 1900 was a Monday.
  * o Thirty days has September,
  *    April, June and November.
  *    All the rest have thirty-one,
  *    Saving February alone,
  *    Which has twenty-eight, rain or shine.
  *    And on leap years, twenty-nine.
  * o A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
  *
  * https://projecteuler.net/problem=19
  **/

var dayOfWeek = 1 //Sunday

def daysOfMonth(y: Int, m: Int): Int = {
  m match {
    case 2 => if (y % 4 == 0 && (y % 100 != 0 || y % 400 == 0)) 29 else 28
    case 4 => 30
    case 6 => 30
    case 9 => 30
    case 11 => 30
    case _ => 31
  }
}




var year = 1900
for (month <- 1 to 12) {
  dayOfWeek += daysOfMonth(year, month)
}


var numOfSundays = 0

for (year <- 1901 to 2000) {
  for (month <- 1 to 12) {

    dayOfWeek = dayOfWeek % 7

    if (dayOfWeek == 0) {
      numOfSundays += 1
    }

    dayOfWeek += daysOfMonth(year, month)

  }
}

println(numOfSundays)