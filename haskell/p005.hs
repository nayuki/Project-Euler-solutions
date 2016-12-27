{- 
 - Solution to Project Euler problem 5
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


-- The smallest number n that is evenly divisible by every number in a set {k1, k2, ..., k_m}
-- is also known as the lowest common multiple (LCM) of the set of numbers.
-- When LCM is applied to a collection of numbers, it is commutative, associative, and idempotent.
-- Hence LCM(k1, k2, ..., k_m) = LCM(...(LCM(LCM(k1, k2), k3)...), k_m).
main = putStrLn (show ans)
ans = foldl1 lcm [1..20]
