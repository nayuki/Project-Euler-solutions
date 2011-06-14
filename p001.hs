ans = sum (filter (\x -> (mod x 3) * (mod x 5) == 0) [0..999])
main = putStrLn (show ans)
