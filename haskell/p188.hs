{- 
 - Solution to Project Euler problem 188
 - by Project Nayuki
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


main = putStrLn (show ans)
ans = tetrationMod 1777 1855 (10^8)

-- Need to use Integer instead of Int to prevent silent overflow

tetrationMod :: Integer -> Integer -> Integer -> Integer
tetrationMod x 1 m = mod x m
tetrationMod x y m = powMod x (tetrationMod x (y - 1) (totient m)) m

powMod :: Integer -> Integer -> Integer -> Integer
powMod _ 0 m = mod 1 m
powMod x y m
	| mod y 2 == 0 = temp
	| otherwise    = mod (temp * x) m
	where
		temp = powMod (mod (x * x) m) (div y 2) m

totient :: Integer -> Integer
totient x = fromIntegral $ sum [1 | y <- [1..x], gcd x y == 1]
