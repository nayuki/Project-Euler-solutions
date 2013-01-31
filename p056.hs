{- 
 - Solution to Project Euler problem 56
 - By Nayuki Minase
 - 
 - http://nayuki.eigenstate.org/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


digitsum 0 = 0
digitsum n = (mod n 10) + (digitsum (div n 10))

ans = foldl1 max [digitsum (a^b) | a <- [0..99], b <- [0..99]]
main = putStrLn (show ans)
