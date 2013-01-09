{- 
 - Solution to Project Euler problem 1
 - By Nayuki Minase
 - 
 - http://nayuki.eigenstate.org/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


ans = sum [x | x <- [0..999], (mod x 3) * (mod x 5) == 0]
main = putStrLn (show ans)
