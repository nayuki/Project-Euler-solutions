{- 
 - Solution to Project Euler problem 14
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


main = putStrLn (show ans)
ans = indexMax (map collatz [0..10^6])

collatz :: Integer -> Int
collatz 0 = 0
collatz 1 = 1
collatz n = (collatz (if mod n 2 == 0 then (div n 2) else (n * 3 + 1))) + 1

indexMax :: [Int] -> Int
indexMax = indexMax' 0 (-1) 0
indexMax' _ j _ [] = j  -- Current index, max index, max value
indexMax' i j v (x:xs) =
	if (j == -1 || x > v)
	then (indexMax' (i+1) i x xs)
	else (indexMax' (i+1) j v xs)
