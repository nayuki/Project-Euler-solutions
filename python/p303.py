# 
# Solution to Project Euler problem 303
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	def find_minimum_multiple(n):
		# issumfeasible.get(i)[j] indicates whether it is possible to form a sum of j mod n
		# using i (rightmost) digits, where each digit is between 0 and 2, and leading digits can be 0.
		# Possible values:
		# - 0: The sum is impossible
		# - 1: The sum is possible but all digits are zero
		# - 2: The sum is possible and at least one digit is non-zero
		
		# Initialization and base case
		issumfeasible = [[1] + [0] * (n - 1)]
		
		# Add digits on the left side until a solution exists, using dynamic programming
		i = 0  # Loop invariant: i == len(issumfeasible) - 1
		while issumfeasible[i][0] != 2:
			prev = issumfeasible[i]
			cur = list(prev)  # Clone
			digitmod = pow(10, i, n)
			for j in range(n):
				if prev[j] > 0:
					cur[(j + digitmod * 1) % n] = 2
					cur[(j + digitmod * 2) % n] = 2
			issumfeasible.append(cur)
			i += 1
		
		# Construct the smallest solution using the memoized table
		solution = 0
		remainder = 0
		temp = i
		for i in reversed(range(temp)):  # Pick digits from left (most significant) to right
			digitmod = pow(10, i, n)
			for j in range((1 if (i == len(issumfeasible) - 2) else 0), 3):  # Pick current digit
				newrem = (remainder - digitmod * j) % n
				if issumfeasible[i][newrem] > 0:
					solution = solution * 10 + j
					remainder = newrem
					break
			else:
				raise AssertionError()
		return solution
	
	ans = sum(find_minimum_multiple(n) // n for n in range(1, 10001))
	return str(ans)


if __name__ == "__main__":
	print(compute())
