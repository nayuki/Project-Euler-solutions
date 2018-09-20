{- 
 - Solution to Project Euler problem 45
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


main = putStrLn (show ans)
ans = compute 286 166 144

compute :: Integer -> Integer -> Integer -> Integer
compute i j k = let
		triangle = div (i * (i + 1)) 2
		pentagon = div (j * (j * 3 - 1)) 2
		hexagon  = k * (k * 2 - 1)
		minim = minimum [triangle, pentagon, hexagon]
	in if (minim == triangle && minim == pentagon && minim == hexagon) then minim
		else compute
			(condInc i (minim == triangle))
			(condInc j (minim == pentagon))
			(condInc k (minim == hexagon ))

condInc :: Integer -> Bool -> Integer
condInc i c = i + (if c then 1 else 0)
