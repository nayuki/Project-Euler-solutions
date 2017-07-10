{- 
 - Solution to Project Euler problem 71
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import Data.Ratio ((%), numerator)
import qualified EulerLib


main = putStrLn (show ans)
ans = numerator $ foldl1 max [((div (d * 3) 7) - (EulerLib.boolToInt ((mod d 7) == 0))) % d | d <- [1..10^6]]
