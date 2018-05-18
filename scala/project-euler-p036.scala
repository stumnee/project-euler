/**
  * Problem 36. Double-base palindromes
  *
  * The decimal number, 585 = 1001001001₂ (binary), is palindromic in both bases.
  *
  * Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.
  *
  * (Please note that the palindromic number, in either base, may not include leading zeros.)
  *
  * https://projecteuler.net/problem=36
  **/

val max = 1000000

def isPalindrome(s: String): Boolean = {
  s == s.reverse
}


var sum = 0

for (i <- 1 to max) {
  if (isPalindrome(i.toString) && isPalindrome(i.toBinaryString)) {
    println(s"${i} = ${i.toBinaryString}")
    sum += i
  }
}

println(s"Sum ${sum}")

/*
1 = 1
3 = 11
5 = 101
7 = 111
9 = 1001
33 = 100001
99 = 1100011
313 = 100111001
585 = 1001001001
717 = 1011001101
7447 = 1110100010111
9009 = 10001100110001
15351 = 11101111110111
32223 = 111110111011111
39993 = 1001110000111001
53235 = 1100111111110011
53835 = 1101001001001011
73737 = 10010000000001001
585585 = 10001110111101110001
Sum 872187

 */