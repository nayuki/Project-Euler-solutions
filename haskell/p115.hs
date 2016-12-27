{- 
 - Solution to Project Euler problem 115
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


{- 
 - How many ways can a row n units long be filled, where red blocks are
 - at least m units long? Denote this quantity as ways[n].
 - Compute n = 0 manually as a base case.
 - 
 - Now assume n >= 1. Look at the leftmost item and sum up the possibilities.
 - * If the item is a black square, then the rest of the row is allowed
 -   to be anything of length n-1. Add ways[n-1].
 - * If the item is a red block with length k where k >= m, then:
 -   * If k = n, then the whole row is filled by this red block. Add 1.
 -   * Otherwise k < n, this red block is followed by a black square, then followed
 -     by anything of length n-k-1. So add ways[n-m-1] + ways[n-m-2] + ... + ways[0].
 -}
m = 50
main = putStrLn (show ans)
ans = findExceeding 0

ways 0 = 1
ways n = (waysMemo !! (n-1)) + (if n >= m then (1 + (sum (take (n-m) waysMemo))) else 0)
waysMemo = map ways [0..]

findExceeding n = if (ways n) > 1000000 then n else (findExceeding (n+1))
