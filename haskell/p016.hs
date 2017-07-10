{- 
 - Solution to Project Euler problem 16
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import qualified EulerLib


-- We implement this solution in a straightforward way thanks to Haskell's built-in arbitrary precision Integer type.
main = putStrLn (show ans)
ans = EulerLib.digitSum (2^1000 :: Integer)
