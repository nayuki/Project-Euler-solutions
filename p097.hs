{- 
 - Solution to Project Euler problem 97
 - By Nayuki Minase
 - 
 - http://nayuki.eigenstate.org/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


main = putStrLn (show ans)
ans = mod (28433 * 2^7830457 + 1) (10^10)
