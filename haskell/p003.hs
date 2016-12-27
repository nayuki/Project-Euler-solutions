{- 
 - Solution to Project Euler problem 3
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


-- By the fundamental theorem of arithmetic, every integer n > 1 has a unique factorization as a product of prime numbers.
-- In other words, the theorem says that n = p_0 * p_1 * ... * p_{m-1}, where each p_i > 1 is prime but not necessarily unique.
-- Now if we take the number n and repeatedly divide out its smallest factor (which must also be prime), then the last
-- factor that we divide out must be the largest prime factor of n. For reference, 600851475143 = 71 * 839 * 1471 * 6857.
main = putStrLn (show ans)
ans = largestPrimeFactor (600851475143 :: Integer)

largestPrimeFactor n =
	let
		p = smallestPrimeFactor n
	in
		if p == n then p
		else largestPrimeFactor (div n p)

smallestPrimeFactor n = head [k | k <- [2..n], mod n k == 0]
