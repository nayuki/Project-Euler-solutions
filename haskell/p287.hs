{- 
 - Solution to Project Euler problem 287
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import Data.Int


{- 
 - Let R = 2^(N-1) denote the radius of the circle (filled disk) being drawn.
 - 
 - First, we can simplify the problem by translating (shifting) the coordinate system.
 - Instead of x and y each in [0, 2^N) for the formula [x - 2^(N-1)]^2 + [y - 2^(N-1)]^2 <= R^2,
 - we shall consider x and y each in [-(2^(N-1)), 2^(N-1)) for the formula x^2 + y^2 <= R^2.
 - 
 - Suppose we are given a square 2D region with endpoints [xStart, xEnd) and [yStart, yEnd).
 - If the region is entirely white or entirely black, then it takes 2 bits to encode the region.
 - Otherwise the region must have both white and black pixels, so we use 1 bit
 - to encode the split, recurse on the 4 sub-squares, and sum their code lengths.
 - 
 - Within the region, what are the possible values of the left side of the formula, x^2 + y^2?
 - To minimize or maximize x^2 + y^2, we can min/maximize each of x^2 and y^2 independently.
 - * To minimize x^2, we minimize |x|. If 0 is in [xStart, xEnd),
 -   then the minimum |x| is 0, and thus the minimum x^2 is 0.
 -   Otherwise, either all possible x values are negative or all
 -   are positive, so the minimum |x| is min(|xStart|, |xEnd-1|).
 - * To maximize x^2, we maximize |x|. This simply equals max(|xStart|, |xEnd-1|).
 - * The same arguments apply to minimizing/maximizing y^2.
 - 
 - Now evaluate minR^2 = minX^2 + minY^2, and maxR^2 = maxX^2 + maxY^2.
 - * If maxR^2 <= R^2, then all points in the region satisfy
 -   x^2 + y^2 <= R^2, hence the entire region is black.
 - * Similarly, if minR^2 > R^2, then all points in the region
 -   satisfy x^2 + y^2 > R^2, hence the entire region is white.
 - * Otherwise, the region must contain both black and white points,
 -   so we split into 4 subregions and recurse.
 -}

n = 24  -- Represents uppercase N
radiusSquared = (2 :: Int64)^(2 * n - 2)

main = putStrLn (show ans)
ans = let temp = (2 :: Int64)^(n - 1)
	in compressedLength (-temp) temp (-temp) temp

compressedLength :: Int64 -> Int64 -> Int64 -> Int64 -> Integer
compressedLength xStart xEnd yStart yEnd = let
		minAbsX = if (xStart <= 0 && 0 < xEnd) then 0 else (min (abs xStart) (abs (xEnd - 1)))
		minAbsY = if (yStart <= 0 && 0 < yEnd) then 0 else (min (abs yStart) (abs (yEnd - 1)))
		maxAbsX = max (abs xStart) (abs (xEnd - 1))
		maxAbsY = max (abs yStart) (abs (yEnd - 1))
		minRadius = minAbsX^2 + minAbsY^2
		maxRadius = maxAbsX^2 + maxAbsY^2
	in if maxRadius <= radiusSquared || minRadius > radiusSquared then  -- All black or all white, respectively
		2
	else let
			xMid = div (xStart + xEnd) 2
			yMid = div (yStart + yEnd) 2
		in (1 +
			(compressedLength xStart xMid yMid   yEnd) +  -- Top left
			(compressedLength xMid   xEnd yMid   yEnd) +  -- Top right
			(compressedLength xStart xMid yStart yMid) +  -- Bottom left
			(compressedLength xMid   xEnd yStart yMid))   -- Bottom right
