{- 
 - Solution to Project Euler problem 73
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


{- 
 - The Stern-Brocot tree is an infinite binary search tree of all positive rational numbers,
 - where each number appears only once and is in lowest terms.
 - It is formed by starting with the two sentinels 0/1 and 1/1. Iterating infinitely in any order,
 - between any two currently adjacent fractions Ln/Ld and Rn/Rd, insert a new fraction (Ln+Rn)/(Ld+Rd).
 - See MathWorld for a visualization: http://mathworld.wolfram.com/Stern-BrocotTree.html
 - 
 - This algorithm uses a lot of stack space (about 12000 frames). You probably need to use a JVM option like "-Xss4M".
 -}
main = putStrLn (show ans)
ans = sternBrocotCount 1 3 1 2


-- Counts the number of reduced fractions n/d such that leftN/leftD < n/d < rightN/rightD and d <= 12000.
-- leftN/leftD and rightN/rightD must be adjacent in the Stern-Brocot tree at some point in the generation process.
sternBrocotCount :: Int -> Int -> Int -> Int -> Integer
sternBrocotCount leftN leftD rightN rightD = let
		n = leftN + rightN
		d = leftD + rightD
	in if (d > 12000) then 0
		else 1 + (sternBrocotCount leftN leftD n d) + (sternBrocotCount n d rightN rightD)
