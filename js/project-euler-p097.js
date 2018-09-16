var n = 1
let max = Math.pow(10, 10)
let power = 7830457

for (var i = 1; i <= power; i++) {
	n *= 2
	if (n > max) {
		n = n % max
	}
}

console.log(28433 * n % max + 1)

// 8739992577
