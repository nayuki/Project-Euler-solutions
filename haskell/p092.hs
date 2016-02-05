{- 
 - Solution to Project Euler problem 92
 - by Project Nayuki
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


main = putStrLn (show ans)
ans = length (filter arrive89 [1 .. (10^7 - 1)])

arrive89 :: Int -> Bool
arrive89 1 = False
arrive89 89 = True
arrive89 n = arrive89 (squareDigitSum n)

squareDigitSum 0 = 0
squareDigitSum n = (mod n 10)^2 + (squareDigitSum (div n 10))
