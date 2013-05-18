{- 
 - Solution to Project Euler problem 301
 - By Nayuki Minase
 - 
 - http://nayuki.eigenstate.org/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


{- 
 - In a game of Nim where both players play with perfect strategy, if the current state is a collection (multiset) of piles
 - with sizes {n1, n2, ..., n_m}, then the current player will lose if and only if n1 XOR n2 XOR ... XOR n_m = 0.
 - In this problem, we specialize the condition to just n XOR 2n XOR 3n = 0.
 - 
 - Facts:
 -   3n = 2n + n.
 -   a ^ b = 0 iff a = b. (from digital logic)
 -   a + b = (a ^ b) + ((a & b) << 1). (from digital logic)
 - Hence:
 -   n ^ 2n ^ 3n = 0                          (what we want)
 -   iff n ^ 2n = 3n                          (from the second fact)
 -   iff n ^ 2n = (n ^ 2n) + ((n & 2n) << 1)  (from the third fact)
 -   iff (n & 2n) << 1 = 0                    (by cancelling on both sides)
 -   iff n & 2n = 0                           (left-shifting doesn't change zeroness)
 -   iff the binary representation of n does not have consecutive '1' bits.
 -}
main = putStrLn (show ans)
ans = (numStrings0 !! 30) + (numStrings1 !! 30)
-- The value (numStrings0 !! 30) + (numStrings1 !! 30) is almost the final answer we want. It considers bit strings in the range [0, 2^30).
-- But according to the problem description, we want to exclude 0 and include 2^30. Both are losing positions, so the adjustments cancel out.

-- numStrings_j !! i is the number of bit strings with length i that begin with the bit j and has no consecutive 1's
numStrings0 = 0 : 1 : [(numStrings0 !! (i-1)) + (numStrings1 !! (i-1)) | i <- [2..]]
numStrings1 = 0 : 1 : (tail numStrings0)
