{- 
 - Solution to Project Euler problem 301
 - By Nayuki Minase
 - 
 - http://nayuki.eigenstate.org/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import Data.Bits (xor)


main = putStrLn (show ans)
ans = length [() | i <- ([1..2^30] :: [Integer]), i `xor` (i*2) `xor` (i*3) == 0]
