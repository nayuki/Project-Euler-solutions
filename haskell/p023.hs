{- 
 - Solution to Project Euler problem 23
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


limit = 28123
main = putStrLn (show ans)
ans = sum (filter (not . isSumOfTwoAbundants) [1..limit])

isSumOfTwoAbundants n = any (\k -> isAbundant (n - k)) (takeWhile (< n) abundants)
abundants = filter isAbundant [1..]
isAbundant n = (divisorSum n) > n

divisorSum n = (sum [k + (div n k) | k <- upToSqrt, mod n k == 0]) - n - (if sqrt^2 == n then sqrt else 0)
	where upToSqrt = takeWhile (\k -> k * k <= n) [1..]
	      sqrt = length upToSqrt
