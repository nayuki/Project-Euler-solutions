{- 
 - Solution to Project Euler problem 55
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import qualified EulerLib


main = putStrLn (show ans)
ans = EulerLib.count (isLychrel 49) [1..9999]

isLychrel 0 n = True
isLychrel iter n = (next /= reverseInt next) && isLychrel (iter - 1) next where
	next = n + (reverseInt n)

reverseInt = revInt 0 where
	revInt acc 0 = acc
	revInt acc n = revInt (acc * 10 + (mod n 10)) (div n 10)
