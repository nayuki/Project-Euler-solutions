{- 
 - Solution to Project Euler problem 32
 - by Project Nayuki
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import Data.Bits (shiftL, shiftR)
import Data.List (sort)


main = putStrLn (show ans)
ans = sum [n | n <- [1..9999], hasPandigitalProduct n]

hasPandigitalProduct :: Int -> Bool
hasPandigitalProduct n = or [pandigital ((digits n) ++ (digits i) ++ (digits (div n i)))
	| i <- [1..(sqrtInt n)+1], mod n i == 0]

digits :: Int -> [Int]
digits 0 = [0]
digits n = digits' n where
	digits' 0 = []
	digits' n = (digits' (div n 10)) ++ [mod n 10]

pandigital :: [Int] -> Bool
pandigital d = (sort d) == [1..9]

-- sqrtInt n = floor(sqrt(n)).
-- Implemented entirely in integer arithmetic; guaranteed no rounding error.
sqrtInt :: Int -> Int
sqrtInt n = sqrtAlpha 1 where
	sqrtAlpha i
		| i * i > n = sqrtBeta (shiftR i 1) 0
		| otherwise = sqrtAlpha (shiftL i 1)
	sqrtBeta 0 acc = acc
	sqrtBeta i acc = sqrtBeta (div i 2) (if (i + acc)^2 <= n then i + acc else acc)
