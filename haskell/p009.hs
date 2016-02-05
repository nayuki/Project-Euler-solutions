{- 
 - Solution to Project Euler problem 9
 - by Project Nayuki
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


p = 1000
main = putStrLn (show ans)
ans = head [a * b * (p - a - b) | a <- [1..p], b <- [1..p], isRight a b]
isRight a b = a <= b && b <= c
	&& a * a + b * b == c * c
	where c = p - a - b
