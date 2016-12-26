# 
# Solution to Project Euler problem 93
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import fractions, itertools


def compute():
	ans = max(((a, b, c, d)
		for a in range(1, 10)
		for b in range(a + 1, 10)
		for c in range(b + 1, 10)
		for d in range(c + 1, 10)),
		key=longest_consecutive)
	return "".join(str(x) for x in ans)


def longest_consecutive(abcd):
	a, b, c, d = abcd
	expressible = set()
	
	# Try all possible orderings of operands and operators
	ops = [0, 0, 0, a, b, c, d]  # 0 = operator slot, 1 to 9 = literal operand
	while True:
		
		# Try all possibilities for the 3 operators
		for i in range(64):
			stack = []
			j = 0  # Operator index
			
			stackunderflow = False
			divbyzero = False
			for op in ops:
				if 1 <= op <= 9:  # Operand
					stack.append(fractions.Fraction(op))
				elif op == 0:  # Operator
					if len(stack) < 2:
						stackunderflow = True
						break
					right = stack.pop()
					left = stack.pop()
					oper = (i >> (j * 2)) & 3
					if oper == 0:
						stack.append(left + right)
					elif oper == 1:
						stack.append(left - right)
					elif oper == 2:
						stack.append(left * right)
					elif oper == 3:
						if right.numerator == 0:
							divbyzero = True
							break
						stack.append(left / right)
					else:
						raise AssertionError()
					j += 1  # Consume an operator
				else:
					raise AssertionError()
			
			if stackunderflow:
				break
			if divbyzero:
				continue
			if len(stack) != 1:
				raise AssertionError()
			
			result = stack.pop()
			if result.denominator == 1:
				expressible.add(result.numerator)
		
		if not next_permutation(ops):
			break
	
	# Find largest set of consecutive expressible integers starting from 1
	return next(i for i in itertools.count(1) if (i not in expressible)) - 1


def next_permutation(arr):
    # Find non-increasing suffix
    i = len(arr) - 1
    while i > 0 and arr[i - 1] >= arr[i]:
        i -= 1
    if i <= 0:
        return False
    
    # Find successor to pivot
    j = len(arr) - 1
    while arr[j] <= arr[i - 1]:
        j -= 1
    arr[i - 1], arr[j] = arr[j], arr[i - 1]
    
    # Reverse suffix
    arr[i : ] = arr[len(arr) - 1 : i - 1 : -1]
    return True


if __name__ == "__main__":
	print(compute())
