{- 
 - Solution to Project Euler problem 15
 - By Nayuki Minase
 - 
 - http://nayuki.eigenstate.org/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


factorial 0 = 1
factorial n = n * (factorial (n - 1))

binomial n k = div (factorial n) ((factorial (n - k)) * (factorial k))

ans = binomial 40 20

main = putStrLn (show ans)
