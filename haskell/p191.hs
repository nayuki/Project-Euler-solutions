{- 
 - Solution to Project Euler problem 191
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


main = putStrLn (show ans)
ans = sum (map sum (nps !! numDays))

numDays = 30
maxAbsent = 2
maxLate = 1

-- This is the number of prize strings of length d with exactly
-- a absences at the tail and exactly l lates in the whole string
numPrizeStrings 0 0 0 = 1
numPrizeStrings 0 _ _ = 0
numPrizeStrings d l 0 = (sum (nps !! (d - 1) !! l)) + (if l > 0 then (sum (nps !! (d - 1) !! (l - 1))) else 0)
numPrizeStrings d l a = nps !! (d - 1) !! l !! (a - 1)
nps = [[[numPrizeStrings d l a | a <- [0..maxAbsent]] | l <- [0..maxLate]] | d <- [0..numDays]]  -- Memoization
