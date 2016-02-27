# 
# Solution to Project Euler problem 91
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	LIMIT = 51
	ans = sum(1
		for x1 in range(LIMIT)
		for y1 in range(LIMIT)
		for x2 in range(LIMIT)
		for y2 in range(LIMIT)
		# For uniqueness, ensure that (x1,y1) has a larger angle than (x2,y2)
		if y2 * x1 < y1 * x2 and is_right_triangle(x1, y1, x2, y2))
	return str(ans)


# Tests whether {(0,0), (x1,y1), (x2,y2)} forms a right triangle.
def is_right_triangle(x1, y1, x2, y2):
	dx = x2 - x1
	dy = y2 - y1
	a = x1 * x1 + y1 * y1
	b = x2 * x2 + y2 * y2
	c = dx * dx + dy * dy
	return a + b == c or b + c == a or c + a == b


if __name__ == "__main__":
	print(compute())
