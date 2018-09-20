{- 
 - Solution to Project Euler problem 46
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import qualified EulerLib


main = putStrLn (show ans)
ans = head $ filter (not . satisfiesConjecture) [9, 11 .. ]

satisfiesConjecture :: Integer -> Bool
satisfiesConjecture n = (mod n 2) == 0 || EulerLib.isPrime n ||
	any (\i -> EulerLib.isPrime (n - i * i * 2)) (takeWhile (\i -> i * i * 2 <= n) [1 .. ])
