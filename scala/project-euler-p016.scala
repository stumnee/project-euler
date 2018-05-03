/**
  * Problem 16. Power digit sum
  *
  * 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
  *
  * What is the sum of the digits of the number 2^1000?
  *
  * https://projecteuler.net/problem=16
  **/


val max = 1000
var p: BigInt = 2

//for (i <- 2 to max) {
//  p *= 2
//}


p = p.pow(1000)
println(p)

val sum = p.toString().map(_ - '0').sum

println(sum)

/*
10715086071862673209484250490600018105614048117055336074437503883703510511249361224931983788156958581275946729175531468251871452856923140435984577574698574803934567774824230985421074605062371141877954182153046474983581941267398767559165543946077062914571196477686542167660429831652624386837205668069376

1366
*/