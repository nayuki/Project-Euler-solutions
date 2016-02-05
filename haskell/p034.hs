{- 
 - Solution to Project Euler problem 34
 - by Project Nayuki
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


main = putStrLn (show ans)
ans = sum [i | i <- [3..10^7 - 1], i == factorialDigitSum i]

factorialDigitSum 0 = 0
factorialDigitSum n = (factorial !! (mod n 10)) + (factorialDigitSum (div n 10))

-- Hard-coded values for factorial(0), factorial(1), ..., factorial(9)
factorial = [1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880]
