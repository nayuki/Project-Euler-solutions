{- 
 - Solution to Project Euler problem 92
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import qualified EulerLib


limit = 10^7
main = putStrLn (show ans)
ans = EulerLib.count arrive89 [1 .. (limit - 1)]

arrive89 :: Int -> Bool
arrive89 1 = False
arrive89 89 = True
arrive89 n = arrive89 (squareDigitSum n)

squareDigitSum :: Int -> Int
squareDigitSum 0 = 0
squareDigitSum n = (mod n 10)^2 + (squareDigitSum (div n 10))
