{- 
 - Solution to Project Euler problem 132
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import Data.Bits (shiftL, shiftR)


main = putStrLn (show ans)
cond i = isPrime i && (repunitMod (10^9) i) == 0
ans = sum (take 40 (filter cond [2..]))

repunitMod :: Integer -> Integer -> Integer
repunitMod k m = div (powMod 10 k (m * 9) - 1) 9

powMod :: Integer -> Integer -> Integer -> Integer
powMod _ 0 _ = 1
powMod x 1 m = mod x m
powMod x y m = mod ((powMod x (div y 2) m)^2 * (if ((mod y 2) == 0) then 1 else x)) m

isPrime :: Integer -> Bool
isPrime n = n > 1 && null [() | k <- [2 .. (sqrtInt n)], mod n k == 0]

-- sqrtInt n = floor(sqrt(n)).
-- Implemented entirely in integer arithmetic; guaranteed no rounding error.
sqrtInt :: Integer -> Integer
sqrtInt n = sqrtAlpha 1 where
	sqrtAlpha i
		| i * i > n = sqrtBeta (shiftR i 1) 0
		| otherwise = sqrtAlpha (shiftL i 1)
	sqrtBeta 0 acc = acc
	sqrtBeta i acc = sqrtBeta (div i 2) (if (i + acc)^2 <= n then i + acc else acc)
