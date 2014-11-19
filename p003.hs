{- 
 - Solution to Project Euler problem 3
 - by Project Nayuki
 - 
 - http://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


main = putStrLn (show ans)
ans = largestPrimeFactor (600851475143 :: Integer)

largestPrimeFactor n =
	let
		p = smallestPrimeFactor n
	in
		if p == n then p
		else largestPrimeFactor (div n p)

smallestPrimeFactor n = head [k | k <- [2..n], mod n k == 0]
