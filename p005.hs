{- 
 - Solution to Project Euler problem 5
 - By Nayuki Minase
 - 
 - http://nayuki.eigenstate.org/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


ans = foldl1 lcm [1..20]
main = putStrLn (show ans)
