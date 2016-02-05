{- 
 - Solution to Project Euler problem 29
 - by Project Nayuki
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import List (nub)


main = putStrLn (show ans)
ans = length (nub [a^b | a <- [2..100], b <- [2..100]])
