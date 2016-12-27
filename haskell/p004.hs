{- 
 - Solution to Project Euler problem 4
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


-- Computers are fast, so we can implement this solution directly without any clever math.
main = putStrLn (show ans)
ans = foldl1 max [a * b | a <- [100..999], b <- [100..999], isPalindrome (a * b)]

reverseInt = revInt 0 where
	revInt acc 0 = acc
	revInt acc n = revInt (acc * 10 + (mod n 10)) (div n 10)

isPalindrome n = n == (reverseInt n)
