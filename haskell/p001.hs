{- 
 - Solution to Project Euler problem 1
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


-- Computers are fast, so we can implement this solution directly without any clever math.
main = putStrLn (show ans)
ans = sum [x | x <- [0..999], (mod x 3) == 0 || (mod x 5) == 0]
