{- 
 - Solution to Project Euler problem 40
 - By Nayuki Minase
 - 
 - http://nayuki.eigenstate.org/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


digits 0 = [0]
digits n = reverse (digits' n)
digits' 0 = []
digits' n = (mod n 10) : (digits' (div n 10))

decimal = foldr (\n rest -> digits n ++ rest) [] [1..]

ans = product [decimal !! (10^i - 1) | i <- [0..6]]

main = putStrLn (show ans)
