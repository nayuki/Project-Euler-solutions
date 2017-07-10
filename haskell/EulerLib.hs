{- 
 - Shared code for solutions to Project Euler problems
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

module EulerLib
	(boolToInt, count, factorial, binomial, digitSum, sqrt, isPrime)
	where

import Prelude hiding (sqrt)
import Data.Bits


boolToInt :: Integral a => Bool -> a
boolToInt False = 0
boolToInt True  = 1


-- Counts the number of elements in the list that satisfy the predicate.
count :: (a -> Bool) -> [a] -> Int
count pred list = length (filter pred list)
-- Or shorter: count = length . filter


factorial :: Integral a => a -> Integer
factorial n = product [1 .. (toInteger n)]


binomial :: Integral a => a -> a -> Integer
binomial n k = div (product [(toInteger (n - k + 1)) .. (toInteger n)]) (factorial k)


digitSum :: Integral a => a -> a
digitSum 0 = 0
digitSum n = (mod n 10) + (digitSum (div n 10))


-- sqrt n = floor(sqrt(n)).
-- Implemented entirely in integer arithmetic; guaranteed no rounding error.
sqrt :: (Integral a, Data.Bits.Bits a) => a -> a
sqrt n = sqrtAlpha 1 where
	sqrtAlpha i
		| i * i > n = sqrtBeta (Data.Bits.shiftR i 1) 0
		| otherwise = sqrtAlpha (Data.Bits.shiftL i 1)
	sqrtBeta 0 acc = acc
	sqrtBeta i acc = sqrtBeta (div i 2) (if (i + acc)^2 <= n then i + acc else acc)


isPrime :: (Integral a, Data.Bits.Bits a) => a -> Bool
isPrime n = n > 1 && null [() | k <- [2 .. (sqrt n)], mod n k == 0]
