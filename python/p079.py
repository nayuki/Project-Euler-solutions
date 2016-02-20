# 
# Solution to Project Euler problem 79
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import itertools


def compute():
	# Try ascending lengths
	for len in itertools.count(3):
		for i in range(10**len):
			guess = str(i).zfill(len)
			if is_consistent(guess):
				return guess


def is_consistent(guess):
	return all(is_subsequence(s, guess) for s in SUBSEQS)


def is_subsequence(shortstr, longstr):
	i = 0
	for c in longstr:
		if c == shortstr[i]:
			i += 1
			if i == len(shortstr):
				return True
	return False


SUBSEQS = ["319", "680", "180", "690", "129", "620", "762", "689", "762", "318", "368", "710", "720", "710", "629", "168", "160", "689", "716", "731", "736", "729", "316", "729", "729", "710", "769", "290", "719", "680", "318", "389", "162", "289", "162", "718", "729", "319", "790", "680", "890", "362", "319", "760", "316", "729", "380", "319", "728", "716"]


if __name__ == "__main__":
	print(compute())
