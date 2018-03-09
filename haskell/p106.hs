{- 
 - Solution to Project Euler problem 106
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import qualified EulerLib


setSize = 12
main = putStrLn (show ans)
catalan n = div (EulerLib.binomial (n * 2) n) (n + 1)
ans = sum [(EulerLib.binomial setSize (i * 2)) * ((div (EulerLib.binomial (i * 2) i) 2) - (catalan i))
	| i <- [2 .. (div setSize 2)]]
