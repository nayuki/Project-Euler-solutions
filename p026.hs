{- 
 - Solution to Project Euler problem 26
 - By Nayuki Minase
 - 
 - http://nayuki.eigenstate.org/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import Data.List (elemIndex)


main = putStrLn (show ans)
ans = argMax cycleLength [1..1000]

cycleLength n =
	let
		remainders = iterate (\x -> mod (x * 10) n) 1
		findCycle [] (x:xs) = findCycle (x:[]) xs
		findCycle acc (x:xs) = case elemIndex x acc of
			Nothing -> findCycle (x:acc) xs
			Just i -> i + 1
	in
		findCycle [] remainders

argMax f xs = fst (argMax' f xs)
argMax' f (x:xs) = if xs == [] || temp > v then (x, temp) else (a, v) where
	(a, v) = argMax' f xs
	temp = f x
