var num = 1000;

var main = function(num) {
	var total = 0;
	for (var i = 0; i < num; i++) {
		if (i % 3 == 0 || i % 5 == 0) {
			total += i;
		}
	}
	return total;
}

console.log(main(num));