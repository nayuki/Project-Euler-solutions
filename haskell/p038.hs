{- 
 - Solution to Project Euler problem 38
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import Data.List (sort)


main = putStrLn (show ans)
ans = foldl1 max $ concat [[number (concatProd n x) | x <- [1 .. 10 ^ (div 9 n) - 1], pandigital (concatProd n x)] | n <- [2..9]]

digits 0 = [0]
digits n = digits' n where
	digits' 0 = []
	digits' n = (digits' (div n 10)) ++ [mod n 10]

number d = number' d 0 where
	number' [] acc = acc
	number' (d:ds) acc = number' ds (acc * 10 + d)

pandigital d = (sort d) == [1..9]

concatProd n x = concat [digits (x * i) | i <- [1..n]]
