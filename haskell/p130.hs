{- 
 - Solution to Project Euler problem 130
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import qualified EulerLib


main = putStrLn (show ans)
cond i = (mod i 5 /= 0) && (not (EulerLib.isPrime i)) && mod (i - 1) (findLeastDivisibleRepunit i) == 0
ans = sum (take 25 (filter cond [7,9..]))

findLeastDivisibleRepunit :: Integer -> Integer
findLeastDivisibleRepunit n = if (mod n 2) == 0 || (mod n 5) == 0
	then 0
	else let
		func 0 _ k = k
		func s p k = func (mod (s + p * 10) n) (mod (p * 10) n) (k + 1)
	in func 1 1 1
