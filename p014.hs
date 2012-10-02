-- Solution to Project Euler problem 14
-- By Nayuki Minase


collatz 0 = 0
collatz 1 = 1
collatz n = (collatz (if mod n 2 == 0 then (div n 2) else (n * 3 + 1))) + 1

argmax xs = argmax' 0 (-1) 0 xs
argmax' _ j _ [] = j
argmax' i j v (x:xs) = if (j == -1 || x > v)
	then (argmax' (i+1) i x xs)
	else (argmax' (i+1) j v xs)

ans = argmax (map collatz [0..10^6])

main = putStrLn (show ans)
