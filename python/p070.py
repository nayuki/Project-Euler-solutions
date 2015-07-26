# 
# Solution to Project Euler problem 70
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	totients = list(range(10000000))
	for i in range(2, len(totients)):
		if totients[i] == i:  # i is prime
			for j in range(i, len(totients), i):
				totients[j] = totients[j] // i * (i - 1)
	
	minnumer = 1
	mindenom = 0
	for (i, tot) in enumerate(totients[2 : ], 2):
		if sorted(str(i)) == sorted(str(tot)) and i * mindenom < minnumer * tot:
			minnumer = i
			mindenom = totients[i]
	return str(minnumer)


if __name__ == "__main__":
	print(compute())
