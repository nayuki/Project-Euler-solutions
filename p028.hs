{- 
 - Solution to Project Euler problem 28
 - By Nayuki Minase
 - 
 - http://nayuki.eigenstate.org/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


main = putStrLn (show ans)
ans = 1 + sum [let n = (i * 2 + 3); n2 = (n * n) in (n2 + (n2 - (n - 1)) + (n2 - (n - 1) * 2) + (n2 - (n - 1) * 3)) | i <- [0..499]]
