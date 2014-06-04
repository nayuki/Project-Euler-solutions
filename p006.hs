{- 
 - Solution to Project Euler problem 6
 - By Nayuki Minase
 - 
 - http://nayuki.eigenstate.org/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


{- 
 - For the mathematically inclined:
 -   1   + 2   + ... + n   = n(n + 1) / 2.
 -   1^2 + 2^2 + ... + n^2 = n(n + 1)(2n + 1) / 6.
 -}
n = 100
main = putStrLn (show ans)
ans = (sum [1..n])^2 - (sum (map (^2) [1..n]))
