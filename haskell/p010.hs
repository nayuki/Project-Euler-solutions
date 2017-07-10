{- 
 - Solution to Project Euler problem 10
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import EulerLib


main = putStrLn (show ans)
ans = sum (filter isPrime [2 .. 2000000-1])

isPrime :: Int -> Bool
isPrime n = n > 1 && null [() | k <- [2 .. (EulerLib.sqrt n)], mod n k == 0]
