{- 
 - Solution to Project Euler problem 58
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import Data.Ratio ((%))
import qualified EulerLib


{- 
 - From the diagram, let's observe the four corners of an n * n square (where n is odd).
 - It's not hard to convince yourself that:
 - * The bottom right corner always has the value n^2.
 - Working clockwise (backwards):
 - * The bottom left corner has the value n^2 - (n - 1).
 - * The top left corner has the value n^2 - 2(n - 1).
 - * The top right has the value n^2 - 3(n - 1).
 - 
 - Furthermore, the number of elements on the diagonal is 2n - 1.
 -}
target = 1 % 10
main = putStrLn (show ans)
ans = compute 0 1

compute :: Integer -> Integer -> Integer
compute numPrimes n = let newNumPrimes = numPrimes + (sum [1 | i <- [0..3], EulerLib.isPrime (n^2 - i * (n - 1))])
	in if (n > 1 && newNumPrimes % (n * 2 - 1) < target) then n
	else compute newNumPrimes (n + 2)
