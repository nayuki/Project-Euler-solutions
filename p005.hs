{- 
 - Solution to Project Euler problem 5
 - By Nayuki Minase
 -}


ans = foldl1 lcm [1..20]
main = putStrLn (show ans)
