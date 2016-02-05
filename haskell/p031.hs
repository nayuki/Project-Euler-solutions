{- 
 - Solution to Project Euler problem 31
 - by Project Nayuki
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


main = putStrLn (show ans)
ans = ways coins 200

coins = [1, 2, 5, 10, 20, 50, 100, 200]

ways [] 0 = 1
ways [] _ = 0
ways (c:cs) n = sum [ways cs (n - i * c) | i <- [0..(div n c)]]
