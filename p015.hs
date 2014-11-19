{- 
 - Solution to Project Euler problem 15
 - by Project Nayuki
 - 
 - http://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


main = putStrLn (show ans)
ans = (binomial 40 20) :: Integer
binomial n k = div (product [(n - k + 1) .. n]) (product [1..k])
