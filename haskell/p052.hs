{- 
 - Solution to Project Euler problem 52
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import Data.List (sort)


main = putStrLn (show ans)
ans = head (filter cond [1 .. ])
cond x = all (multeq x) [2 .. 6]
multeq x i = (sort (show x)) == (sort (show (i * x)))
