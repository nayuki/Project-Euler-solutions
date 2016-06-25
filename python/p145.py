# 
# Solution to Project Euler problem 145
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


# A slow brute-force answer.
def compute():
	ODD_DIGITS = {"1", "3", "5", "7", "9"}
	
	def is_sum_reversible_odd(x):
		xstr = str(x)
		if xstr[-1] == "0":
			return False  # The reverse can't have leading zero
		revx = int(xstr[::-1])
		sumstr = str(x + revx)
		return all(c in ODD_DIGITS for c in sumstr)
	
	ans = sum(1 for i in range(10**9) if is_sum_reversible_odd(i))
	return str(ans)


if __name__ == "__main__":
	print(compute())
