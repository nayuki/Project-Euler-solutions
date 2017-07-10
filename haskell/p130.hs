{- 
 - Solution to Project Euler problem 130
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import Data.Bits (shiftL, shiftR)


main = putStrLn (show ans)
cond i = (mod i 5 /= 0) && (not (isPrime i)) && mod (i - 1) (findLeastDivisibleRepunit i) == 0
ans = sum (take 25 (filter cond [7,9..]))

findLeastDivisibleRepunit :: Integer -> Integer
findLeastDivisibleRepunit n = if (mod n 2) == 0 || (mod n 5) == 0
	then 0
	else let
		func 0 _ k = k
		func s p k = func (mod (s + p * 10) n) (mod (p * 10) n) (k + 1)
	in func 1 1 1

isPrime :: Integer -> Bool
isPrime n = n > 1 && null [() | k <- [2 .. (sqrtInt n)], mod n k == 0]

-- sqrtInt n = floor(sqrt(n)).
-- Implemented entirely in integer arithmetic; guaranteed no rounding error.
sqrtInt :: Integer -> Integer
sqrtInt n = sqrtAlpha 1 where
	sqrtAlpha i
		| i * i > n = sqrtBeta (shiftR i 1) 0
		| otherwise = sqrtAlpha (shiftL i 1)
	sqrtBeta 0 acc = acc
	sqrtBeta i acc = sqrtBeta (div i 2) (if (i + acc)^2 <= n then i + acc else acc)
