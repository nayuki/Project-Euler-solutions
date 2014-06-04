{- 
 - Solution to Project Euler problem 20
 - By Nayuki Minase
 - 
 - http://nayuki.eigenstate.org/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


main = putStrLn (show ans)
ans = digitSum (factorial 100 :: Integer)

digitSum 0 = 0
digitSum n = (mod n 10) + (digitSum (div n 10))

factorial n = product [1..n]
