{- 
 - Solution to Project Euler problem 53
 - By Nayuki Minase
 - 
 - http://nayuki.eigenstate.org/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


factorial 0 = 1
factorial n = n * (factorial (n - 1))

binomial n k = div (factorial n) ((factorial (n - k)) * (factorial k))

ans = sum [1 | n <- [1..100], r <- [1..n], binomial n r > 1000000]

main = putStrLn (show ans)
