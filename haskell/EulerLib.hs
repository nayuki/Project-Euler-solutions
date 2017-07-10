{- 
 - Shared code for solutions to Project Euler problems
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

module EulerLib
	(digitSum)
	where


digitSum :: Integral a => a -> a
digitSum 0 = 0
digitSum n = (mod n 10) + (digitSum (div n 10))
