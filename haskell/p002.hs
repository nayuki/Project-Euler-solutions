{- 
 - Solution to Project Euler problem 2
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


-- Computers are fast, so we can implement this solution directly without any clever math.
main = putStrLn (show ans)
ans = sum $ filter even $ takeWhile (<= 4000000) fibonacci

-- A lazy infinite sequence of numbers
fibonacci = 1 : 2 : (zipWith (+) fibonacci (tail fibonacci))
