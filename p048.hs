ans = mod (sum (map (\k -> k^k) [1..1000])) (10^10)
main = putStrLn (show ans)
