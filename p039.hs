{- 
 - Solution to Project Euler problem 39
 - By Nayuki Minase
 - 
 - http://nayuki.eigenstate.org/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


main = putStrLn (show ans)
ans = argmax numRightTriangles [1..1000]

numRightTriangles p = length [() | a <- [1..p], b <- [a..(div (p-a) 2)], let c = p-a-b in a*a + b*b == c*c]

argmax f xs = arg where
	(arg, val) = argmax' f xs
	argmax' f (x:[]) = (x, f x)
	argmax' f (x:xs) = if temp > v then (x, temp) else (a, v) where
		temp = f x
		(a, v) = argmax' f xs
