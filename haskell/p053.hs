{- 
 - Solution to Project Euler problem 53
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import qualified EulerLib


main = putStrLn (show ans)
ans = sum [1 | n <- [1..100], r <- [0..n], EulerLib.binomial n r > 10^6]
