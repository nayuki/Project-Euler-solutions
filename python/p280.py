# 
# Solution to Project Euler problem 280
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import eulerlib, itertools


# Model the problem as a Markov process, and solve using dynamic programming
def compute():
	# Memoize the successors of each valid state
	successors = {}
	for st in State.list_all_states():
		successors[st.id] = [s.id for s in st.get_successors()]
	
	# Run the simulation
	ans = 0.0
	probs = {State.START_STATE.id: 1.0}  # The current probability of being in each state
	for i in itertools.count(1):
		# Note: The done state has no outgoing neighbors,
		# so its probability disappears in the next iteration
		nextprobs = {}
		for (j, p) in probs.items():
			suc = successors[j]
			for k in suc:
				nextprobs[k] = nextprobs.get(k, 0.0) + probs[j] / float(len(suc))
		
		donenow = nextprobs.get(State.DONE_STATE.id, 0.0)
		if i > 44 and donenow < 1e-20:  # Note: Minimum completion is 44 steps
			break
		ans += donenow * i
		probs = nextprobs
	return f"{ans:.6f}"



# Represents the global state of the system, including the ant and seeds. Immutable.
class State:
	
	# All valid state IDs are in the range [0, ID_LIMIT). Not every number in the range is a valid state.
	ID_LIMIT = 5 * 5 * 2**11 + 1
	
	@staticmethod
	def list_all_states():
		result = set()
		# Try all 2^11 ways for which cells (or ant) hold a seed
		for i in range(2**11):
			if eulerlib.popcount(i) != 5:
				continue  # Invalid state if not 5 things hold a seed
				
			# For all 5*5 possible ant positions
			for y in range(5):
				for x in range(5):
					seed = [((i >> j) & 1) != 0 for j in range(11)]
					result.add(State(False, x, y, seed))
		result.add(State.DONE_STATE)
		return result
	
	
	def __init__(self, done, x, y, seed):
		self.isdone = done
		self.antx = x
		self.anty = y
		self.hasseed = seed
		if done:
			self.id = 5 * 5 * 2**11
		else:
			temp = sum((1 if b else 0) << i for (i, b) in enumerate(seed))
			self.id = x + y * 5 + temp * 25
	
	
	# Returns a set (of size 0 to 4) containing this state's successors.
	# Remember that this state transitions to a successor with equal probability.
	def get_successors(self):
		result = set()
		if not self.isdone:
			self.try_add_successor(-1, 0, result)
			self.try_add_successor(+1, 0, result)
			self.try_add_successor(0, -1, result)
			self.try_add_successor(0, +1, result)
		return result
	
	
	def try_add_successor(self, dx, dy, result):
		x = self.antx + dx
		y = self.anty + dy
		if not (0 <= x < 5 and 0 <= y < 5):
			return  # Ant moves off the grid
		
		seed = list(self.hasseed)
		done = False
		if not seed[10] and y == 4 and seed[5 + x]:  # Pick up seed
			seed[5 + x] = False
			seed[10] = True
		elif seed[10] and y == 0 and not seed[x]:  # Drop off seed
			seed[10] = False
			seed[x] = True
			done = seed[0] & seed[1] & seed[2] & seed[3] & seed[4]
		result.add(State(done, x, y, seed))

State.START_STATE = State(False, 2, 2, [False, False, False, False, False, True, True, True, True, True, False])
State.DONE_STATE  = State(True , 0, 0, [True, True, True, True, True, False, False, False, False, False, False])



if __name__ == "__main__":
	print(compute())
