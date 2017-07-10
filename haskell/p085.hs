{- 
 - Solution to Project Euler problem 85
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import Data.List (foldl1')
import qualified EulerLib


target = 2000000
main = putStrLn (show ans)
ans = let
		end = EulerLib.sqrt target
		list = [(w, h) | w <- [1..end], h <- [1..end]]
		func (w, h) = abs ((numberOfRectangles w h) - target)
		(bestW, bestH) = argMin func list
	in bestW * bestH

numberOfRectangles :: Integer -> Integer -> Integer
numberOfRectangles m n = div ((m + 1) * m * (n + 1) * n) 4  -- A bit more than m^2 n^2 / 4

-- The earliest element x in the list such that (f x) <= (f y) for each element y in the list.
argMin :: Ord b => (a -> b) -> [a] -> a
argMin f = foldl1' (\x y -> if (f y) < (f x) then y else x)
