{- 
 - Solution to Project Euler problem 3
 - By Nayuki Minase
 - 
 - http://nayuki.eigenstate.org/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


main = putStrLn (show ans)
ans = largestPrimeFactor 600851475143

largestPrimeFactor n
	| smallestPrimeFactor == n = n
	| otherwise = largestPrimeFactor (div n smallestPrimeFactor)
	where
		smallestPrimeFactor = head [k | k <- [2..n], mod n k == 0]
