{- 
 - Solution to Project Euler problem 2
 - By Nayuki Minase
 - 
 - http://nayuki.eigenstate.org/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


main = putStrLn (show ans)
ans = sum $ filter even $ takeWhile (<= 4000000) fibonacci

fibonacci = 1 : 2 : (zipWith (+) fibonacci (tail fibonacci))
