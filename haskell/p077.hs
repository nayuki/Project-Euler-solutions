{- 
 - Solution to Project Euler problem 77
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import qualified EulerLib


target = 5000
main = putStrLn (show ans)
ans = head $ dropWhile (\n -> partitions n n <= target) [0..]

{- 
 - Let P(i, n) denote the number of ways that n can be written as an
 - unordered sum of prime numbers where no prime is greater than i.
 - 
 - * P(i, 0) = 1 for all i.
 - * P(0, n) = 0 for all n > 0.
 - * If i is 1 or composite then P(i, n) = P(i - 1, n).
 - * Otherwise i is prime:
 -   * If i <= n then P(i, n) = P(i - 1, n) + P(i, n - i).
 -   * Else P(i, n) = P(i - 1, n).
 -}
partitions :: Int -> Int -> Int
partitions _ 0 = 1
partitions 0 _ = 0
partitions i n = (if ((EulerLib.isPrime i) && i <= n) then (partitionsMemo !! i !! (n - i)) else 0) + (partitionsMemo !! (i - 1) !! n)
partitionsMemo = [[partitions i n | n <- [0..]] | i <- [0..]]
