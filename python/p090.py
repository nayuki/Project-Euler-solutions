# 
# Solution to Project Euler problem 90
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	# Each die has (10 choose 6) arrangements, so we have at most 44100 arrangements to check
	ans = sum(1
		for i in range(1 << 10)
		for j in range(i, 1 << 10)  # Ensure i <= j to force the dice to be orderless
		# If both have Hamming weight of 6
		if bin(i).count("1") == bin(j).count("1") == 6 and is_arrangement_valid(i, j))
	return str(ans)


def is_arrangement_valid(a, b):
	if test_bit(a, 6) or test_bit(a, 9):
		a |= (1 << 6) | (1 << 9)
	if test_bit(b, 6) or test_bit(b, 9):
		b |= (1 << 6) | (1 << 9)
	return all(((test_bit(a, c) and test_bit(b, d)) or (test_bit(a, d) and test_bit(b, c)))
		for (c, d) in SQUARES)


def test_bit(x, i):
	return ((x >> i) & 1) != 0


SQUARES = ((0, 1), (0, 4), (0, 9), (1, 6), (2, 5), (3, 6), (4, 9), (6, 4), (8, 1))


if __name__ == "__main__":
	print(compute())
