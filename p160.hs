-- Solution to Project Euler problem 160
-- By Nayuki Minase


md n = mod n (10 ^ 5)  -- Modulo reduction

powMod _ 0 = 1
powMod x y | mod y 2 == 0 = powMod (md $ x * x) (div y 2)
           | otherwise    = md $ (powMod (md $ x * x) (div y 2)) * x

-- factorialLast
f n = md $ (g n) * (powMod 2 ((c 2 n) - (c 5 n)))

-- factorialish
g n = md $ (ge n) * (go n)

-- evenFactorialish
ge 0 = 1
ge n = g (div n 2)

-- oddFactorialish
go 0 = 1
go n = md $ (go (div n 5)) * (h n)

-- factorialoid
h n = foldl (\x y -> md $ x * y) 1 (filter (\k -> (mod k 2) * (mod k 5) /= 0) [1..md n])

-- countFactors
c n 0 = 0
c n m = (div m n) + (c n (div m n))

-- main
ans = f (10^12)
main = putStrLn (show ans)
