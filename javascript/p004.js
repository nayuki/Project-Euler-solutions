var main = function() {
	var largest = 0;
	
	for (var i = 0; i < 1000; i++) {
		for(var j = 0; j < 1000; j++) {
			var current = i * j;
			if (isPalindrome(current) && current > largest) {
				console.log(i, j, current, largest);
				largest = current;
			}
		}
	}
	
	return largest;
}

var isPalindrome = function(num) {
	num = num.toString();
	var temp = num.split("").reverse().join("");
	return num === temp;
}


console.log("Largest Palindrome: " + main());