{- 
 - Solution to Project Euler problem 25
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


{- 
 - Because the target number is relatively small, we simply compute each Fibonacci number starting
 - from the beginning until we encounter one with exactly 1000 digits. The Fibonacci sequence grows
 - exponentially with a base of about 1.618, so the numbers in base 10 will lengthen by one digit
 - after every log10(1.618) ~= 4.78 steps on average. This means the answer is at index around 4780.
 -}

digits = 1000
main = putStrLn (show ans)
ans = length (takeWhile (< 10^(digits-1)) fibonacci)

-- The Fibonacci sequence as an infinite list, implemented via tail chasing.
fibonacci :: [Integer]
fibonacci = 0 : 1 : (zipWith (+) fibonacci (tail fibonacci))
