# 
# Solution to Project Euler problem 287
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


# Let R = 2^(N-1) denote the radius of the circle (filled disk) being drawn.
# 
# First, we can simplify the problem by translating (shifting) the coordinate system.
# Instead of x and y each in [0, 2^N) for the formula [x - 2^(N-1)]^2 + [y - 2^(N-1)]^2 <= R^2,
# we shall consider x and y each in [-(2^(N-1)), 2^(N-1)) for the formula x^2 + y^2 <= R^2.
# 
# Suppose we are given a square 2D region with endpoints [xstart, xend) and [ystart, yend).
# If the region is entirely white or entirely black, then it takes 2 bits to encode the region.
# Otherwise the region must have both white and black pixels, so we use 1 bit
# to encode the split, recurse on the 4 sub-squares, and sum their code lengths.
# 
# Within the region, what are the possible values of the left side of the formula, x^2 + y^2?
# To minimize or maximize x^2 + y^2, we can min/maximize each of x^2 and y^2 independently.
# - To minimize x^2, we minimize |x|. If 0 is in [xstart, xend),
#   then the minimum |x| is 0, and thus the minimum x^2 is 0.
#   Otherwise, either all possible x values are negative or all
#   are positive, so the minimum |x| is min(|xstart|, |xend-1|).
# - To maximize x^2, we maximize |x|. This simply equals max(|xstart|, |xend-1|).
# - The same arguments apply to minimizing/maximizing y^2.
# 
# Now evaluate minR^2 = minX^2 + minY^2, and maxR^2 = maxX^2 + maxY^2.
# - If maxR^2 <= R^2, then all points in the region satisfy
#   x^2 + y^2 <= R^2, hence the entire region is black.
# - Similarly, if minR^2 > R^2, then all points in the region
#   satisfy x^2 + y^2 > R^2, hence the entire region is white.
# - Otherwise, the region must contain both black and white points,
#   so we split into 4 subregions and recurse.
# 
# One further optimization: If the region [xstart, xend) * [ystart, yend) lies
# entirely within a quadrant, then calculating minR and maxR becomes trivial.
# In fact, only the root call to compressed_length() spans both positive
# and negative coordinates; all deeper calls are entirely within a quadrant.
# For a region with [xstart, xend) where xstart < xend <= 0, compressed_length()
# yields the same result when the range is replaced with [-xend + 1, -xstart + 1).
# Hence by symmetry, we can only consider cases where 0 <= xstart < xend,
# and not deal with negative ranges. This optimized bit length algorithm can
# no longer be adapted to encode the actual compressed bit stream, however.
def compute():
	N = 24
	RADIUS_SQUARED = 2**(2 * N - 2)
	
	# Returns the exact minimum number of bits required to encode
	# the circle image's region of [xstart, end) * [ystart, yend),
	# requiring 0 <= xstart < xend and 0 <= ystart < yend.
	def compressed_length(xstart, xend, ystart, yend):
		if xstart * xstart + ystart * ystart > RADIUS_SQUARED:  # All white
			return 2
		elif (xend - 1) * (xend - 1) + (yend - 1) * (yend - 1) <= RADIUS_SQUARED:  # All black
			return 2
		else:  # Subdivide and recurse
			xmid = (xstart + xend) >> 1
			ymid = (ystart + yend) >> 1
			return (1 +
				compressed_length(xstart, xmid, ymid  , yend) +  # Top left
				compressed_length(xmid  , xend, ymid  , yend) +  # Top right
				compressed_length(xstart, xmid, ystart, ymid) +  # Bottom left
				compressed_length(xmid  , xend, ystart, ymid))   # Bottom right
	
	temp = 2**(N - 1)
	return str(1 +
		compressed_length(0, temp, 0, temp) +
		compressed_length(0, temp, 1, temp + 1) +
		compressed_length(1, temp + 1, 0, temp) +
		compressed_length(1, temp + 1, 1, temp + 1))


if __name__ == "__main__":
	print(compute())
