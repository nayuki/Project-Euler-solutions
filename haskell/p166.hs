{- 
 - Solution to Project Euler problem 166
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


{- 
 - Give variables to the grid cells like this:
 -   [ a b c d ]
 -   [ e f g h ]
 -   [ i j k l ]
 -   [ m n o p ]
 - There are 10 four-element lines in the grid. Assume each line has a sum of s.
 - We write 9 equations to equate the first row's sum with each other line's sum:
 -   a + b + c + d  =  e + f + g + h.  (1st row = 2nd row)
 -   a + b + c + d  =  i + j + k + l.  (1st row = 3rd row)
 -   a + b + c + d  =  m + n + o + p.  (1st row = 4th row)
 -   a + b + c + d  =  a + e + i + m.  (1st row = 1st column)
 -   a + b + c + d  =  b + f + j + n.  (1st row = 2nd column)
 -   a + b + c + d  =  c + g + k + o.  (1st row = 3rd column)
 -   a + b + c + d  =  d + h + l + p.  (1st row = 4th column)
 -   a + b + c + d  =  a + f + k + p.  (1st row = diagonal)
 -   a + b + c + d  =  d + g + j + m.  (1st row = antidiagonal)
 - Actually only 8 of these equations are linearly independent.
 - So with 16 variables and 8 equations, we have 8 free variables.
 - Suppose we know the values of {a,b,c,d,e,g,i,k}. We perform some algebra:
 -   m = b + c + d - e - i.  (based on 1st column)
 -   o = a + b + d - g - k.  (based on 3rd column)
 -   j = a + b + c - g - m.  (based on antidiagonal)
 -   l = a + b + c + d - i - j - k.  (based on 3rd row)
 - Now comes a difficult step. We need to solve a full system of linear equations to get the rest of the values.
 - Here I state the result for f and show its correctness, but not explain how the solution was found:
 -   - 1 (a + b + c + d)     - 1 (m + n + o + p)
 -   + 2 (a + b + c + d)     + 2 (a + e + i + m)
 -   + 1 (a + b + c + d)  =  + 1 (b + f + j + n)  .
 -   + 1 (a + b + c + d)     + 1 (c + g + k + o)
 -   + 1 (a + b + c + d)     + 1 (a + f + k + p)
 -   - 1 (a + b + c + d)     - 1 (d + g + j + m)
 - Adding up all terms on the left side and on the right side, and simplifying, we get:
 -   3(a + b + c + d) = 3a + 2e + 2i + b + 2f + c + 2k - d.
 -   f = b + c + 2d - e - i - k.
 - The rest of the way is smooth sailing:
 -   h = a + b + c + d - e - f - g.  (based on 2nd row)
 -   n = a + c + d - f - j.  (based on 2nd column)
 -   p = a + b + c - h - l.  (based on 4th column)
 -}

main = putStrLn (show ans)
digits = [0 .. 9] :: [Int]
ans = sum [1 |
	b <- digits,
	c <- digits,
	d <- digits,
	e <- digits,
	i <- digits,
	k <- digits,
	a <- digits,
	g <- digits,
	let
		m = b + c + d - e - i
		f = b + c + d*2 - e - i - k
		o = a + b + d - g - k
		j = a + b + c - g - m
		l = a + b + c + d - i - j - k
		h = a + b + c + d - e - f - g
		n = a + c + d - f - j
		p = a + b + c - h - l
	in
		isDigit m &&
		isDigit f &&
		isDigit o &&
		isDigit j &&
		isDigit l &&
		isDigit h &&
		isDigit n &&
		isDigit p]

isDigit :: Int -> Bool
isDigit n = 0 <= n && n <= 9
