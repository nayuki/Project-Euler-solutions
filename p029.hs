{- 
 - Solution to Project Euler problem 29
 - By Nayuki Minase
 -}


import List (nub)

ans = length (nub [a ^ b | a <- [2..100], b <- [2..100]])
main = putStrLn (show ans)
