{- 
 - Solution to Project Euler problem 225
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


index = 124  -- 1-based
main = putStrLn (show ans)
ans = (filter hasNoTribonacciMultiple [1,3..]) !! (index - 1)

hasNoTribonacciMultiple :: Int -> Bool
hasNoTribonacciMultiple n = let
		tribonacci (a, b, c) = (b, c, mod (a + b + c) n)
		func init slow@(a, _, _) fast
			| mod a n == 0 = False
			| not init && slow == fast = True
			| otherwise = func False (tribonacci slow) (tribonacci (tribonacci fast))
	in func True (1,1,1) (1,1,1)
