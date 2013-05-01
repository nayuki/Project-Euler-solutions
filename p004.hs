{- 
 - Solution to Project Euler problem 4
 - By Nayuki Minase
 - 
 - http://nayuki.eigenstate.org/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


reverseInt = revInt 0 where
	revInt acc 0 = acc
	revInt acc n = revInt (acc * 10 + (mod n 10)) (div n 10)

isPalindrome n = n == (reverseInt n)

ans = foldl1 max [a * b | a <- [100..999], b <- [100..999], isPalindrome (a * b)]
main = putStrLn (show ans)
