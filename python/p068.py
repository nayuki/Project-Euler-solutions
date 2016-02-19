# 
# Solution to Project Euler problem 68
# by Project Nayuki
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	state = list(range(1, 11))
	max = None
	while True:
		sum = state[0] + state[5] + state[6]
		if state[1] + state[6] + state[7] == sum and \
		   state[2] + state[7] + state[8] == sum and \
		   state[3] + state[8] + state[9] == sum and \
		   state[4] + state[9] + state[5] == sum:
			
			minouterindex = 0
			minouter = state[0]
			for i in range(1, 5):
				if state[i] < minouter:
					minouterindex = i
					minouter = state[i]
			
			s = ""
			for i in range(5):
				s += str(state[(minouterindex + i) % 5])
				s += str(state[(minouterindex + i) % 5 + 5])
				s += str(state[(minouterindex + i + 1) % 5 + 5])
			if len(s) == 16 and (max is None or s > max):
				max = s
		
		if not next_permutation(state):
			break
	
	assert max is not None
	return max


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
