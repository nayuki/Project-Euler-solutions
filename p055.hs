{- 
 - Solution to Project Euler problem 55
 - By Nayuki Minase
 - 
 - http://nayuki.eigenstate.org/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


main = putStrLn (show ans)
ans = length $ filter (isLychrel 49) [1..9999]

isLychrel 0 n = True
isLychrel iter n = (next /= reverseInt next) && isLychrel (iter - 1) next where
	next = n + (reverseInt n)

reverseInt = revInt 0 where
	revInt acc 0 = acc
	revInt acc n = revInt (acc * 10 + (mod n 10)) (div n 10)
