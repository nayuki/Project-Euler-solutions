{- 
 - Solution to Project Euler problem 139
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


{- 
 - Pythagorean triples theorem:
 -   Every primitive Pythagorean triple with a odd and b even can be expressed as
 -   a = st, b = (s^2-t^2)/2, c = (s^2+t^2)/2, where s > t > 0 are coprime odd integers.
 -}
limit = 100000000
main = putStrLn (show ans)
sRange = takeWhile (\s -> div (s^2) 2 < limit) [3,5..]
ans = sum (map (func 0 1) sRange)

func acc t s = let
		a = s * t
		b = div (s^2 - t^2) 2
		c = div (s^2 + t^2) 2
		p = a + b + c
	in if t >= s || p >= limit
		then acc
		else func (acc + (if (mod c (a - b) == 0 && gcd s t == 1) then (div (limit - 1) p) else 0)) (t + 2) s
