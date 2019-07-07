var main = function() {
	var i = 20;
	var flag = 1;
	while(true) {
		flag = 1;
		for (var j = 2; j < 30; j++) {
			//console.log(i, j);
			if (i % j != 0) {
				//console.log(i, j);
				flag = 0;
				break;
			}
		}
		if (flag) {
			return i;
		}
		i += 1;
	}
}

console.log("Smallest Multiple: " + main());