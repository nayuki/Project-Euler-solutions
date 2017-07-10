{- 
 - Solution to Project Euler problem 56
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import qualified EulerLib


main = putStrLn (show ans)
ans = foldl1 max [EulerLib.digitSum (a^b) | a <- [0..99], b <- [0..99]]
