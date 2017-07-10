{- 
 - Solution to Project Euler problem 20
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import qualified EulerLib


{- 
 - We do a straightforward product thanks to Haskell's built-in arbitrary precision Integer type.
 -}

main = putStrLn (show ans)
ans = EulerLib.digitSum (EulerLib.factorial 100)
