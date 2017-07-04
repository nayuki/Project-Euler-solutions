{- 
 - Solution to Project Euler problem 123
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import Data.Numbers.Primes (primes)  -- From https://hackage.haskell.org/package/primes


-- Using the result from Project Euler #120, we know that (a-1)^n + (a+1)^n mod a^2 = if n is even then 2 else 2an. Since 2 is tiny, we can skip the n is even case.
-- a is the n'th (1-based) prime number, so a > n. In fact for n >= 5, we have a > 2n, so we can take 2an directly without moduloing it by a^2.
main = putStrLn (show ans)

-- Equivalent to this slower code: head $ dropWhile (\n -> n * 2 * (primes !! (n - 1)) <= 10^10) [5, 7 ..]
ans = findFirst 5 (drop 4 primes)

findFirst n (p:_:ps)
	| n * 2 * p > 10^10 = n
	| otherwise = findFirst (n + 2) ps
