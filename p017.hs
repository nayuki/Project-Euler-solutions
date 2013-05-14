{- 
 - Solution to Project Euler problem 17
 - By Nayuki Minase
 - 
 - http://nayuki.eigenstate.org/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


main = putStrLn (show ans)
ans = sum [length (toEnglish n) | n <- [1..1000]]

toEnglish n
	| n < 100 = tens n
	| otherwise = (if n >= 1000 then (tens (div n 1000)) ++ "thousand" else "") ++
	              (if (mod (div n 100) 10 /= 0) then (ones (mod (div n 100) 10)) ++ "hundred" else "") ++
	              (if mod n 100 /= 0 then "and" ++ (tens (mod n 100)) else "")

tens n
	| n < 10 = ones n
	| n < 20 = ["ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"] !! (n - 10)
	| otherwise = (["twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"] !! ((div n 10) - 2)) ++ (if (mod n 10 /= 0) then ones (mod n 10) else "")

ones = (["zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"] !!)
