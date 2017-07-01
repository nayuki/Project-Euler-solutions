# 
# Solution to Project Euler problem 208
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


def compute():
	LIMIT = 70
	
	# Returns a new state tuple.
	def move(state, acw):
		dir, xcomp0, xcomp1, ycomp0, ycomp1, ycomp2 = state
		entry = MOVE_TABLE[dir][0 if acw else 1]
		xcomp0 += entry[0]
		xcomp1 += entry[1]
		ycomp0 += entry[2]
		ycomp1 += entry[3]
		ycomp2 += entry[4]
		temp = min(ycomp0, ycomp1 // 2, ycomp2 // 2)
		ycomp0 -= temp * 1
		ycomp1 -= temp * 2
		ycomp2 -= temp * 2
		dir = (dir + (1 if acw else -1)) % 5
		return (dir, xcomp0, xcomp1, ycomp0, ycomp1, ycomp2)
	
	reachable = {(0, 0, 0, 0, 0, 0): 1}
	for _ in range(LIMIT):
		newreachable = {}
		for (state, ways) in reachable.items():
			acwst = move(state, True )
			cwst  = move(state, False)
			newreachable[acwst] = newreachable.get(acwst, 0) + ways
			newreachable[cwst ] = newreachable.get(cwst , 0) + ways
		reachable = newreachable
	
	ans = sum(reachable.get((dir, 0, 0, 0, 0, 0), 0) for dir in range(5))
	return str(ans)


MOVE_TABLE = (
	(( 0, +1, 0, 1, 0), ( 0, -1, 0, 1, 0)),
	((-1,  0, 0, 0, 1), ( 0, +1, 0, 1, 0)),
	(( 0,  0, 1, 0, 0), (-1,  0, 0, 0, 1)),
	((+1,  0, 0, 0, 1), ( 0,  0, 1, 0, 0)),
	(( 0, -1, 0, 1, 0), (+1,  0, 0, 0, 1)),
)


if __name__ == "__main__":
	print(compute())
