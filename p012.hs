{- 
 - Solution to Project Euler problem 12
 - By Nayuki Minase
 - 
 - http://nayuki.eigenstate.org/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import Data.Bits (shiftL, shiftR)


triangleNumber i = div (i * (i + 1)) 2

divisors :: Int -> Int
divisors n = (length [() | k <- [1 .. (sqrtInt n)], mod n k == 0]) * 2 - (if (sqrtInt n)^2 == n then 1 else 0)

-- sqrtInt n = floor(sqrt(n)).
-- Implemented entirely in integer arithmetic; guaranteed no rounding error.
sqrtInt :: Int -> Int
sqrtInt n = sqrtAlpha 1 where
	sqrtAlpha i
		| i * i > n = sqrtBeta (shiftR i 1) 0
		| otherwise = sqrtAlpha (shiftL i 1)
	sqrtBeta 0 acc = acc
	sqrtBeta i acc = sqrtBeta (div i 2) (if (i + acc)^2 <= n then i + acc else acc)

ans = head $ filter (\n -> (divisors n) > 500) $ map triangleNumber [0..]
main = putStrLn (show ans)
