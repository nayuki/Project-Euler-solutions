{- 
 - Solution to Project Euler problem 97
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


main = putStrLn (show ans)
ans = mod (28433 * 2^7830457 + 1) (10^10) :: Integer
