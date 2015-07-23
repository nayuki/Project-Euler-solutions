# 
# Solution to Project Euler problem 43
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	ans = 0
	num = list(range(10))
	while True:
		if is_substring_divisible(num):
			ans += int("".join(map(str, num)))
		if not next_permutation(num):
			break
	return str(ans)


DIVISIBILITY_TESTS = [2, 3, 5, 7, 11, 13, 17]

def is_substring_divisible(num):
	for i in range(7):
		x = num[i + 1] * 100 + num[i + 2] * 10 + num[i + 3]
		if x % DIVISIBILITY_TESTS[i] != 0:
			return False
	return True


def next_permutation(arr):
	i = len(arr) - 1
	while i > 0 and arr[i - 1] >= arr[i]:
		i -= 1
	if i <= 0:
		return False
	j = len(arr) - 1
	while arr[j] <= arr[i - 1]:
		j -= 1
	arr[i - 1], arr[j] = arr[j], arr[i - 1]
	arr[i : ] = arr[len(arr) - 1 : i - 1 : -1]
	return True


if __name__ == "__main__":
	print(compute())
