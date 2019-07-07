var num = 600851475143;

var main = function(num) {
	while(true) {
		if (isPrime(num)) {
			return num;
		}
		for (var i = 0; i < num; i++) {
			if (isPrime(i) && num % i == 0) {
				num = num / i;
			}
		}
	}
}

var isPrime = function(n) {
	for (var i = 2; i < Math.ceil(Math.sqrt(n)); i++) {
		if (n % i == 0) {
			return false;
		}
	}
	return true;
}

console.log(main(num));