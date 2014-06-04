{- 
 - Solution to Project Euler problem 15
 - By Nayuki Minase
 - 
 - http://nayuki.eigenstate.org/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


main = putStrLn (show ans)
ans = binomial 40 20 :: Integer
binomial n r = div (product [n-r+1..n]) (product [1..r])
