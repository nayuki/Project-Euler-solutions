{- 
 - Solution to Project Euler problem 20
 - By Nayuki Minase
 - 
 - http://nayuki.eigenstate.org/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


factorial n = product [1..n]

digits 0 = [0]
digits n = reverse (digits' n) where
	digits' 0 = []
	digits' n = (mod n 10) : (digits' (div n 10))

ans = sum (digits (factorial 100))

main = putStrLn (show ans)
