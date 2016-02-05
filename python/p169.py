# 
# Solution to Project Euler problem 169
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	NUMBER = 10**25
	ans = count_ways(NUMBER, NUMBER.bit_length() - 1, 2, {})
	return str(ans)


def count_ways(number, exponent, repetitions, ways):
	key = (number, exponent, repetitions)
	if key not in ways:
		if exponent < 0:
			result = 1 if number == 0 else 0
		else:
			result = count_ways(number, exponent - 1, 2, ways)
			power = 1 << exponent
			upper = power * (repetitions + 2)
			if repetitions > 0 and power <= number and number < upper:
				result += count_ways(number - power, exponent, repetitions - 1, ways)
		ways[key] = result
	return ways[key]


if __name__ == "__main__":
	print(compute())
