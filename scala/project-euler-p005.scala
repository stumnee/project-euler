/**
  * Problem 5. Smallest multiple
  *
  * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
  *
  * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
  *
  * https://projecteuler.net/problem=5
  *
  */



var num = 2520

val multiplesOf = (2 to 20).toList

while (!multiplesOf.forall(x => num % x == 0)) {
  num = num + 20
}

println(num)