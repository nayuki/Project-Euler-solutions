# 
# Solution to Project Euler problem 17
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


# - For the numbers 0 to 19, we write the single word:
#   {zero, one, two, three, four, five, six, seven, eight, nine,
#   ten, eleven, twelve, thirteen, fourteen, fifteen, sixteen, seventeen, eighteen, nineteen}.
# - For the numbers 20 to 99, we write the word for the tens place:
#   {twenty, thirty, forty, fifty, sixty, seventy, eighty, ninety}.
#   Subsequently if the last digit is not 0, then we write the word for the ones place (one to nine).
# - For the numbers 100 to 999, we write the ones word for the hundreds place followed by "hundred":
#   {one hundred, two hundred, three hundred, ..., eight hundred, nine hundred}.
#   Subsequently if the last two digits are not 00, then we write the word "and"
#   followed by the phrase for the last two digits (from 01 to 99).
# - For the numbers 1000 to 999999, we write the word for the three digits starting at the
#   thousands place and going leftward, followed by "thousand". Subsequently if the last three
#   digits are not 000, then we write the phrase for the last three digits (from 001 to 999).
def compute():
	ans = sum(len(to_english(i)) for i in range(1, 1001))
	return str(ans)


def to_english(n):
	if 0 <= n < 20:
		return ONES[n]
	elif 20 <= n < 100:
		return TENS[n // 10] + (ONES[n % 10] if (n % 10 != 0) else "")
	elif 100 <= n < 1000:
		return ONES[n // 100] + "hundred" + (("and" + to_english(n % 100)) if (n % 100 != 0) else "")
	elif 1000 <= n < 1000000:
		return to_english(n // 1000) + "thousand" + (to_english(n % 1000) if (n % 1000 != 0) else "")
	else:
		raise ValueError()


ONES = ["zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
        "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"]
TENS = ["", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"]


if __name__ == "__main__":
	print(compute())
