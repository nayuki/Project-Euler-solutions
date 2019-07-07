var main = function() {
	var total = 0;
	
	var current = 0;
	var oneBehind = 1;
	var twoBehind = 1;
	
	while(current < 4000000) {
		current = oneBehind + twoBehind;
		twoBehind = oneBehind;
		oneBehind = current;
		
		//console.log(current);
		
		if (current % 2 == 0) {
			total += current;
		}
	}
	return total;
}

console.log(main());