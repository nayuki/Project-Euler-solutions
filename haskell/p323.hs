{- 
 - Solution to Project Euler problem 323
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import Numeric (showFFloat)


{- 
 - Suppose that n 32-bit integers have been OR'd together.
 - For any arbitrary bit:
 -   The probability that it is 0 is 1/2^n.
 -   The probability that it is 1 is 1 - 1/2^n.
 - Thus for the entire number:
 -   The probability that all bits are 1 is (1 - 1/2^n)^32.
 -     This is the cumulative distribution function that we want.
 -   The probability that some bit is 0 is 1 - (1 - 1/2^n)^32.
 - 
 - The probability density function is simply pdf(n) = cdf(n) - cdf(n-1).
 - So the expected value of the index where the number becomes all 1's is
 - sum(n * pdf(n) for n = 0 to infinity).
 - 
 - This program computes an approximate answer using floating-point, not guaranteed to be correct.
 - Moreover, it truncates the infinite series by ignoring insignificant contributions to the sum.
 - However, the Mathematica version of the solution is exact.
 -}
main = putStrLn (show ans)
ans = (showFFloat (Just 10) expectedValue) ""
expectedValue = sum $ zipWith (*) (takeWhile (> 1e-20) (tail pdf)) [1..]

-- Probability density function
pdf = 0 : (zipWith (-) (tail cdf) cdf)

-- Cumulative distribution function
cdf = [(1 - 2**(-n))^32 | n <- [0..]] :: [Double]
