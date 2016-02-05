# 
# Solution to Project Euler problem 151
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	ans = get_expected_singles((1,), {}) - 2
	return "{:.6f}".format(ans)


def get_expected_singles(state, memo):
	if state not in memo:
		result = 0.0
		if len(state) > 0:
			for i in range(len(state)):
				tempstate = list(state)
				sheet = state[i]
				del tempstate[i]
				for j in range(sheet + 1, 6):
					tempstate.append(j)
				tempstate.sort()
				result += get_expected_singles(tuple(tempstate), memo)
			result /= len(state)
			if len(state) == 1:
				result += 1.0
		memo[state] = result
	return memo[state]


if __name__ == "__main__":
	print(compute())
