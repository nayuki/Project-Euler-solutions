{- 
 - Solution to Project Euler problem 117
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


{- 
 - How many ways can a row n units long be filled with:
 - * grey squares 1 unit long
 - * Red tiles 2 units long
 - * Green tiles 3 units long
 - * Blue tiles 4 units long
 - Denote this quantity as ways[n].
 - 
 - Compute n = 0 manually as a base case.
 - Now assume n >= 1. Look at the leftmost item and sum up the possibilities.
 - * grey square (n>=1): Rest of the row can be anything of length n-1. Add ways[n-1].
 - * Red tile    (n>=2): Rest of the row can be anything of length n-2. Add ways[n-2].
 - * Green tile  (n>=3): Rest of the row can be anything of length n-3. Add ways[n-3].
 - * Blue tile   (n>=4): Rest of the row can be anything of length n-4. Add ways[n-4].
 -}
main = putStrLn (show ans)
ans = ways 50

ways 0 = 1
ways n = sum $ take (min n 4) $ drop (max (n-4) 0) waysMemo
waysMemo = map ways [0..]
