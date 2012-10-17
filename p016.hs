{- 
 - Solution to Project Euler problem 16
 - By Nayuki Minase
 - 
 - http://nayuki.eigenstate.org/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


digits 0 = [0]
digits n = reverse (digits' n)
digits' 0 = []
digits' n = (mod n 10) : (digits' (div n 10))

ans = sum (digits (2^1000))

main = putStrLn (show ans)
