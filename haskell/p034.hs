{- 
 - Solution to Project Euler problem 34
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import qualified EulerLib


main = putStrLn (show ans)
ans = sum [i | i <- [3..10^7 - 1], i == factorialDigitSum i]

factorialDigitSum 0 = 0
factorialDigitSum n = (factorial !! (mod n 10)) + (factorialDigitSum (div n 10))

factorial :: [Int]
factorial = map (fromInteger . EulerLib.factorial) [0..9]
