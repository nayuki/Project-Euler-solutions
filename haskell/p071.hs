{- 
 - Solution to Project Euler problem 71
 - by Project Nayuki
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import Data.Ratio ((%), numerator)


main = putStrLn (show ans)
ans = numerator $ foldl1 max [((div (d * 3) 7) - (if (mod d 7) == 0 then 1 else 0)) % d | d <- [1..10^6]]
