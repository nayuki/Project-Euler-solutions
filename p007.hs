{- 
 - Solution to Project Euler problem 7
 - by Project Nayuki
 - 
 - http://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


main = putStrLn (show ans)
ans = primes !! 10000

primes = sieve [2..] where
	sieve (p:xs) = p : sieve (filter (\x -> mod x p /= 0) xs)
