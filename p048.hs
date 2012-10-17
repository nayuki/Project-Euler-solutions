{- 
 - Solution to Project Euler problem 48
 - By Nayuki Minase
 - 
 - http://nayuki.eigenstate.org/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


ans = mod (sum (map (\k -> k^k) [1..1000])) (10^10)
main = putStrLn (show ans)
