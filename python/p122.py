# 
# Solution to Project Euler problem 122
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


# Uses the concepts of addition chains. See: http://en.wikipedia.org/wiki/Addition_chain
def compute():
	LIMIT = 200
	INFINITY = 1 << 30
	minchainlength = [0] + [INFINITY] * LIMIT
	
	def explore_chains(chain):
		largest = chain[-1]
		n = len(chain)
		if n - 1 > minchainlength[largest]:
			return
		minchainlength[largest] = n - 1
		for i in reversed(range(n)):
			for j in reversed(range(0, i + 1)):
				next = chain[i] + chain[j]
				if largest < next <= LIMIT:
					chain.append(next)
					explore_chains(chain)
					del chain[-1]
	
	explore_chains([1])
	return str(sum(minchainlength))


if __name__ == "__main__":
	print(compute())
