{- 
 - Solution to Project Euler problem 19
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


{- 
 - We use Zeller's congruence to compute the day of week when given the year, month, and day.
 - Then we simply check the first day of all the months in the given range by brute force.
 - 
 - Zeller's congruence is well-known and a bit long to explain.
 - See: https://en.wikipedia.org/wiki/Zeller%27s_congruence
 -}

main = putStrLn (show ans)
ans = sum [1 | y <- [1901..2000], m <- [1..12], dayOfWeek y m 1 == 0]

-- Return value: 0 = Sunday, 1 = Monday, ..., 6 = Saturday.
dayOfWeek :: Int -> Int -> Int -> Int
dayOfWeek year month day = mod (y + (div y 4) - (div y 100) + (div (13 * m + 2) 5) + day + 2) 7
	where m1 = mod (month - 3) 4800
	      y  = mod (year + (div m1 12)) 400
	      m  = mod m1 12
