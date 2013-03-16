{- 
 - Solution to Project Euler problem 16
 - By Nayuki Minase
 - 
 - http://nayuki.eigenstate.org/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


digitSum 0 = 0
digitSum n = (mod n 10) + (digitSum (div n 10))
ans = digitSum (2^1000)
main = putStrLn (show ans)
