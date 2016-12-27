{- 
 - Solution to Project Euler problem 26
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import Data.List (elemIndex, foldl1')


main = putStrLn (show ans)
ans = argMax cycleLength [1..1000]

-- Length of the repeating decimal cycle of 1/n.
-- e.g. 1/4 = 0.25(0), which is length 1.
-- e.g. 1/7 = 0.(142857), which is length 6.
cycleLength :: Int -> Int
cycleLength n =
	let
		remainders = iterate (\x -> mod (x * 10) n) 1
		findCycle [] (x:xs) = findCycle (x:[]) xs
		findCycle acc (x:xs) = case elemIndex x acc of
			Nothing -> findCycle (x:acc) xs
			Just i -> i + 1
	in
		findCycle [] remainders

-- The earliest element x in the list such that (f x) >= (f y) for each element y in the list.
argMax :: Ord b => (a -> b) -> [a] -> a
argMax f = foldl1' (\x y -> if (f y) > (f x) then y else x)
