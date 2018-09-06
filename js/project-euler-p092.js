const chain = new Map()

chain.set(1,1).set(89, 89)

var max = 10000000

function getSq(n){
	var s = 0
	while (n > 0) {
		let d = n % 10
		s += d * d
		n = Math.floor(n / 10)
	}
	return s
}
let setChain = (n) => {
	var ch = chain.get(n)
	if (!ch) {
		ch = setChain(getSq(n))
		chain.set(n, ch)
	}
	return ch
}

for (var n = 2; n < max; n++) {
	setChain(n)
}


var count = 0

chain.forEach((v, k, m) => {
	if (v == 89) {
		count++
	}
})

console.log(count)
