{- 
 - Solution to Project Euler problem 25
 - by Project Nayuki
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


digits = 1000
main = putStrLn (show ans)
ans = length (takeWhile (< 10 ^ (digits - 1)) fibonacci)

fibonacci = 0 : 1 : (zipWith (+) fibonacci (tail fibonacci)) :: [Integer]
