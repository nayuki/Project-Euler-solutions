# 
# Solution to Project Euler problem 206
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


# The major optimization is to do arithmetic in base 10 in the main loop, avoiding division and modulo
def compute():
	# Initialize
	n = 1000000000  # The pattern is greater than 10^18, so start searching at 10^9
	
	ndigits = [0] * 10  # In base 10, little-endian
	temp = n
	for i in range(len(ndigits)):
		ndigits[i] = temp % 10
		temp //= 10
	
	n2digits = [0] * 19  # Based on length of pattern
	temp = n * n
	for i in range(len(n2digits)):
		n2digits[i] = temp % 10
		temp //= 10
	
	# Increment and search
	while not is_concealed_square(n2digits):
		# Add 20n + 100 so that n2digits = (n + 10)^2
		add_20n(ndigits, n2digits)
		add_10pow(n2digits, 2)
		
		# Since n^2 ends with 0, n must end with 0
		n += 10
		add_10pow(ndigits, 1)
		# Now n2digits = n^2
	return str(n)


def is_concealed_square(n):
	for i in range(1, 10):  # Scan for 1 to 9
		if n[20 - i * 2] != i:
			return False
	return n[0] == 0  # Special case for 0


def add_10pow(n, i):
	while n[i] == 9:
		n[i] = 0
		i += 1
	n[i] += 1


def add_20n(n, n2):
	carry = 0
	i = 0
	while i < len(n):
		sum = n[i] * 2 + n2[i + 1] + carry
		n2[i + 1] = sum % 10
		carry = sum // 10
		i += 1
	i += 1
	while carry > 0:
		sum = n2[i] + carry
		n2[i] = sum % 10
		carry = sum // 10
		i += 1


if __name__ == "__main__":
	print(compute())
