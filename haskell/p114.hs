{- 
 - Solution to Project Euler problem 114
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


{- 
 - How many ways can a row n units long be filled? Denote this quantity as ways[n].
 - Compute n = 0, 1, 2 manually as base cases.
 - 
 - Now assume n >= 3. Look at the leftmost item and sum up the possibilities.
 - * If the item is a grey square, then the rest of the row is allowed
 -   to be anything of length n-1. Add ways[n-1].
 - * If the item is a red block with length k where k >= 3, then:
 -   * If k = n, then the whole row is filled by this red block. Add 1.
 -   * Otherwise k < n, this red block is followed by a grey square, then followed
 -     by anything of length n-k-1. So add ways[n-4] + ways[n-5] + ... + ways[0].
 -}
main = putStrLn (show ans)
ans = ways 50

ways 0 = 1
ways 1 = 1
ways 2 = 1
ways n = (waysMemo !! (n-1)) + 1 + (sum (take (n-3) waysMemo))
waysMemo = map ways [0..]
