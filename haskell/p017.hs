{- 
 - Solution to Project Euler problem 17
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


{-
 - * For the numbers 0 to 19, we write the single word:
 -   {zero, one, two, three, four, five, six, seven, eight, nine,
 -   ten, eleven, twelve, thirteen, fourteen, fifteen, sixteen, seventeen, eighteen, nineteen}.
 - * For the numbers 20 to 99, we write the word for the tens place:
 -   {twenty, thirty, forty, fifty, sixty, seventy, eighty, ninety}.
 -   Subsequently if the last digit is not 0, then we write the word for the ones place (one to nine).
 - * For the numbers 100 to 999, we write the ones word for the hundreds place followed by "hundred":
 -   {one hundred, two hundred, three hundred, ..., eight hundred, nine hundred}.
 -   Subsequently if the last two digits are not 00, then we write the word "and"
 -   followed by the phrase for the last two digits (from 01 to 99).
 - * For the numbers 1000 to 999999, we write the word for the three digits starting at the
 -   thousands place and going leftward, followed by "thousand". Subsequently if the last three
 -   digits are not 000, then we write the phrase for the last three digits (from 001 to 999).
 -}
main = putStrLn (show ans)
ans = sum [length (toEnglish n) | n <- [1..1000]]

toEnglish n
	| 0 <= n && n < 20 = ones !! n
	| 20 <= n && n < 100 = (tens !! (div n 10)) ++ (if (mod n 10 /= 0) then (ones !! (mod n 10)) else "")
	| 100 <= n && n < 1000 = (ones !! (div n 100)) ++ "hundred"
		++ (if (mod n 100 /= 0) then ("and" ++ toEnglish (mod n 100)) else "")
	| 1000 <= n && n < 1000000 = toEnglish (div n 1000) ++ "thousand"
		++ (if (mod n 1000 /= 0) then (toEnglish (div n 1000)) else "")

ones = ["zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
        "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"]

tens = ["", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"]
