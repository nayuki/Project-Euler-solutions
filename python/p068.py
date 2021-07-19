# 
# Solution to Project Euler problem 68
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib


def compute():
	state = list(range(1, 11))
	max = None
	while True:
		if state[0] + state[5] + state[6] == \
		   state[1] + state[6] + state[7] == \
		   state[2] + state[7] + state[8] == \
		   state[3] + state[8] + state[9] == \
		   state[4] + state[9] + state[5]:
			
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
		
		if not eulerlib.next_permutation(state):
			break
	
	assert max is not None
	return max


if __name__ == "__main__":
	print(compute())
