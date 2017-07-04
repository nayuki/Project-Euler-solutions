{- 
 - Solution to Project Euler problem 60
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import Data.Numbers.Primes (isPrime, primes)  -- From https://hackage.haskell.org/package/primes


-- Arbitrary initial limit for the sum of the set of chosen concatenatable primes.
-- If sufficiently large then the answer will be correct; otherwise it will be -1.
initialLimit = 100000

setSize = 5


main = putStrLn (show ans)
ans = findSmallerSetSum initialLimit (-1)

findSmallerSetSum :: Int -> Int -> Int
findSmallerSetSum limit bestAns = let nextAns = (findSetSum [] limit primes) in
	if nextAns /= -1 then (findSmallerSetSum (nextAns - 1) nextAns) else bestAns

findSetSum :: [Int] -> Int -> [Int] -> Int
findSetSum foundSet limit nextPrimes
	| length foundSet == setSize = sum foundSet
	| otherwise = findCandidate nextPrimes
	where
		findCandidate (p:nextPrimes)
			| p > limit = -1
			| ((all (isConcatPrime p) foundSet) && s /= -1) = s
			| otherwise = findCandidate nextPrimes
			where
				s = findSetSum (p:foundSet) (limit - p) nextPrimes

isConcatPrime :: Int -> Int -> Bool
isConcatPrime x y = (isPrime $ readInt $ show x ++ show y) && (isPrime $ readInt $ show y ++ show x)
	where readInt = read :: String -> Int
