{- 
 - Solution to Project Euler problem 182
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import qualified EulerLib


p = 1009 :: Integer
q = 3643 :: Integer
main = putStrLn (show ans)
numUnconcealedP = countAllUnconcealed p
numUnconcealedQ = countAllUnconcealed q
minUnconcealedP = minimum numUnconcealedP
minUnconcealedQ = minimum numUnconcealedQ
totient = (p - 1) * (q - 1)
ans = let
		cond e = (numUnconcealedP !! (fromInteger (mod e (p - 1)))) == minUnconcealedP
		      && (numUnconcealedQ !! (fromInteger (mod e (q - 1)))) == minUnconcealedQ
	in sum (filter cond [0 .. (totient - 1)])


countAllUnconcealed :: Integer -> [Integer]
countAllUnconcealed prime = let
		func e = if (gcd e (prime - 1)) == 1
			then (countUnconcealed prime e)
			else 10^20  -- Sentinel
	in map func [0 .. (prime - 2)]


countUnconcealed :: Integer -> Integer -> Integer
countUnconcealed modulus e = fromIntegral (EulerLib.count (\m -> (powMod m e modulus) == m) [0 .. (modulus - 1)])


powMod :: Integer -> Integer -> Integer -> Integer
powMod _ 0 m = mod 1 m
powMod x y m
	| mod y 2 == 0 = temp
	| otherwise    = mod (temp * x) m
	where
		temp = powMod (mod (x * x) m) (div y 2) m
