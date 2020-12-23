# 
# Solution to Project Euler problem 22
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


# We apply straightforward algorithms to sort the names, sum the letter values, and multiply by the position.
def compute():
	ans = sum((i + 1) * (ord(c) - ord('A') + 1)
		for (i, name) in enumerate(NAMES)
		for c in name)
	return str(ans)

with open("names.txt", "rb") as f: buf = f.read()
buf = buf.decode() # For PY 3
buf = buf.replace(chr(34),'') # Remove the quotes
NAMES = sorted(buf.split(','))

if __name__ == "__main__":
	print(compute())
