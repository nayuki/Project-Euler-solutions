# 
# Solution to Project Euler problem 166
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


# Give variables to the grid cells like this:
#   a b c d
#   e f g h
#   i j k l
#   m n o p
# 
# Let the sum of any line be s = a + b + c + d = e + f + g + h = etc. = a + f + k + p = etc.
# Now do some algebra:
#    s = d + h + l + p
#      = (s - a - b - c) + (s - e - f - g) + (s - i - j - k) + (s - a - f - k)
#      = 4s + a + b + c + e + f + g + i + j + k + a + f + k.
#   3s = 2a + b + c + e + 2f + g + i + j + 2k.
# Therefore the right-hand side of the last equation must be a multiple of 3.
def compute():
	ans = 0
	num = [0] * 9  # A counter in base 10, little-endian
	keepgoing = True
	while keepgoing:
		while True:  # A single-iteration loop to let us simulate goto
			# Grid guide:
			#   0 1 2 a
			#   3 4 5 b
			#   6 7 8 c
			#   d e f g
			sum = num[0]*2 + num[1] + num[2] + num[3] + num[4]*2 + num[5] + num[6] + num[7] + num[8]*2
			if sum % 3 != 0: break
			sum //= 3  # The sum of each line
			a = sum - num[0] - num[1] - num[2]
			if a < 0 or a > 9: break
			b = sum - num[3] - num[4] - num[5]
			if b < 0 or b > 9: break
			c = sum - num[6] - num[7] - num[8]
			if c < 0 or c > 9: break
			d = sum - num[0] - num[3] - num[6]
			if d < 0 or d > 9: break
			e = sum - num[1] - num[4] - num[7]
			if e < 0 or e > 9: break
			f = sum - num[2] - num[5] - num[8]
			if f < 0 or f > 9: break
			g = sum - d - e - f
			if g < 0 or g > 9: break
			if (a + num[5] + num[7] + d) != sum or (num[0] + num[4] + num[8] + g) != sum: break
			# No need to test 'a + b + c + g == sum' because it's algebraically true
			ans += 1
			break
		keepgoing = increment(num)
	return str(ans)


# Increments the given base-10 counter in little endian, e.g. [9, 5, 1] -> [0, 6, 1].
# Returns true if incremented, otherwise returns false if all elements are already 9.
def increment(num):
	i = 0
	while num[i] == 9:
		num[i] = 0
		i += 1
		if i == len(num):
			return False
	num[i] += 1
	return True


if __name__ == "__main__":
	print(compute())
