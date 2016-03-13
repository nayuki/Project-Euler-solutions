{- 
 - Solution to Project Euler problem 16
 - by Project Nayuki
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


-- We implement this solution in a straightforward way thanks to Haskell's built-in arbitrary precision Integer type.
main = putStrLn (show ans)
ans = digitSum (2^1000 :: Integer)

digitSum 0 = 0
digitSum n = (mod n 10) + (digitSum (div n 10))
