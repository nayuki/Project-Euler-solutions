{- 
 - Solution to Project Euler problem 63
 - By Nayuki Minase
 - 
 - http://nayuki.eigenstate.org/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


main = putStrLn (show ans)
ans = length [() | n <- [1..21], i <- [1..9], (numLength (i^n :: Integer)) == n]

numLength :: Integer -> Int
numLength n | n < 10    = 1
            | otherwise = (numLength (div n 10)) + 1
