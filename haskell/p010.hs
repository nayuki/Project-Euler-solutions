{- 
 - Solution to Project Euler problem 10
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import qualified EulerLib


limit = 2000000 :: Int
main = putStrLn (show ans)
ans = sum (filter EulerLib.isPrime [2 .. (limit - 1)])
