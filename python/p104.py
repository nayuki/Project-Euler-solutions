# 
# Solution to Project Euler problem 104
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	MOD = 10**9
	a = 0
	b = 1
	i = 0
	while True:
		if "".join(sorted(str(a))) == "123456789" and "".join(sorted(str(fibonacci(i)[0])[ : 9])) == "123456789":
			return str(i)
		a, b = b, (a + b) % MOD
		i += 1
	return str(ans)


# Returns the tuple (F(n), F(n+1)).
def fibonacci(n):
    if n == 0:
        return (0, 1)
    else:
        a, b = fibonacci(n // 2)
        c = a * (b * 2 - a)
        d = a * a + b * b
        if n % 2 == 0:
            return (c, d)
        else:
            return (d, c + d)


if __name__ == "__main__":
	print(compute())
