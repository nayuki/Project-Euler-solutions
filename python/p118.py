# 
# Solution to Project Euler problem 118
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	isprime = eulerlib.list_primality(10000)  # Cache for small numbers
	digits = list(range(1, 10))
	
	def count_prime_sets(startindex, prevnum):
		if startindex == len(digits):
			return 1
		else:
			result = 0
			for split in range(startindex + 1, len(digits) + 1):
				num = int("".join(map(str, digits[startindex : split])))
				if num > prevnum and is_prime(num):
					result += count_prime_sets(split, num)
			return result
	
	def is_prime(n):
		if n < len(isprime):
			return isprime[n]
		else:
			return eulerlib.is_prime(n)
	
	ans = 0
	while True:
		ans += count_prime_sets(0, 0)
		if not next_permutation(digits):
			break
	return str(ans)


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
