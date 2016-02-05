{- 
 - Solution to Project Euler problem 21
 - by Project Nayuki
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


main = putStrLn (show ans)
ans = sum [n | n <- [1..10^4], amicable n]

amicable n = let m = divisorSum n in (m /= n) && (divisorSum m) == n
divisorSum n = sum [k | k <- [1..n-1], (mod n k) == 0]
