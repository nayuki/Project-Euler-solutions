-- Solution to Project Euler problem 7
-- By Nayuki Minase


primes = sieve [2..] where
	sieve (p:xs) = p : sieve (filter (\x -> mod x p /= 0) xs)

ans = primes !! 10000
main = putStrLn (show ans)
