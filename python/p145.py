# 
# Solution to Project Euler problem 145
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


# A slow, mostly brute-force answer.
def compute():
	ODD_DIGITS = {"1", "3", "5", "7", "9"}
	
	# This requires x mod 10 != 0.
	def is_reversible(x):
		revx = int(str(x)[::-1])
		sumstr = str(x + revx)
		return all(c in ODD_DIGITS for c in sumstr)
	
	ans = 0
	for digits in range(2, 10):  # Note: There are no 1-digit reversible numbers
		for i in range(1, 10):  # For each leading digit
			start = (i + 0) * (10**(digits - 1))
			end   = (i + 1) * (10**(digits - 1))
			# Split into cases so that the sum of the first digit and
			# last digit is always odd, thus saving about half the work
			if i % 2 == 0:  # So try numbers ending in odd digit
				for j in range(start + 1, end, 2):
					ans += int(is_reversible(j))
			else:  # i % 2 == 1, so try numbers ending in even non-zero digit
				for j in range(start, end, 10):
					ans += int(is_reversible(j + 2))
					ans += int(is_reversible(j + 4))
					ans += int(is_reversible(j + 6))
					ans += int(is_reversible(j + 8))
	return str(ans)


if __name__ == "__main__":
	print(compute())
