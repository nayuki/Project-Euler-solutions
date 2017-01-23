{- 
 - Solution to Project Euler problem 31
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


{- 
 - We use the standard recursive algorithm to solve the subset sum problem, with memoization.
 - The order of the coin values does not matter, but the values need to be unique.
 -}

main = putStrLn (show ans)
ans = ways coins 200

coins = [1, 2, 5, 10, 20, 50, 100, 200]

ways [] 0 = 1
ways [] _ = 0
ways (c:cs) n = sum [ways cs (n - i * c) | i <- [0..(div n c)]]
