var chains = new Map()

chains.set(1,1)
chains.set(89, 89)

var max = 100

function getSq(n){
	var s = 0
	while (n > 0) {
		var d = n % 10
		s += d * d
		n = Math.floor(n / 10)
	}
	return s
}
function setChain(n){
	var ch = chains.get(n)
	if (!ch) {
		ch = setChain(getSq(n))
	}
	return ch
}

for (var n = 2; n < max; n++) {
	setChain(n)
}


var count = 0

chains.forEach(function(v, k, m){
	if (v == 89) {
		count++
	}
})
console.log(count)
