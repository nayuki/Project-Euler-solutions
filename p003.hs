{- 
 - Solution to Project Euler problem 3
 - By Nayuki Minase
 - 
 - http://nayuki.eigenstate.org/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


largestPrimeFactor n
	| smallestPrimeFactor == n = n
	| otherwise = largestPrimeFactor (div n smallestPrimeFactor)
	where
		smallestPrimeFactor = head [k | k <- [2..n], mod n k == 0]

ans = largestPrimeFactor 600851475143
main = putStrLn (show ans)
