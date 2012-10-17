{- 
 - Solution to Project Euler problem 6
 - By Nayuki Minase
 - 
 - http://nayuki.eigenstate.org/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


ans = (sum [1..100]) ^ 2 - (sum (map (^2) [1..100]))
main = putStrLn (show ans)
