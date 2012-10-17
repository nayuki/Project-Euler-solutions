{- 
 - Solution to Project Euler problem 38
 - By Nayuki Minase
 -}


import List (sort)


digits 0 = [0]
digits n = digits' n
digits' 0 = []
digits' n = (digits' (div n 10)) ++ [mod n 10]

number d = number' d 0
number' [] acc = acc
number' (d:ds) acc = number' ds (acc * 10 + d)

pandigital d = (List.sort d) == [1..9]

concatProd n x = concat [digits (x * i) | i <- [1..n]]

ans = foldl1 max $ concat [[number (concatProd n x) | x <- [1 .. 10 ^ (div 9 n) - 1], pandigital (concatProd n x)] | n <- [2..9]]

main = putStrLn (show ans)
