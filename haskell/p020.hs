{- 
 - Solution to Project Euler problem 20
 - by Project Nayuki
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


main = putStrLn (show ans)
ans = digitSum (factorial 100 :: Integer)

digitSum 0 = 0
digitSum n = (mod n 10) + (digitSum (div n 10))

factorial n = product [1..n]
