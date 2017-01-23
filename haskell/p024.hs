{- 
 - Solution to Project Euler problem 24
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


{- 
 - We initialize an array as the lowest permutation of the given digits, which is the sequence
 - (0,1,2,3,4,5,6,7,8,9). Then we call the next permutation algorithm on it 999 999 times
 - (because the index in the problem is 1-based), and stringify the resulting sequence.
 - 
 - The next permutation algorithm is well-known and a bit long to explain.
 - See: https://www.nayuki.io/page/next-lexicographical-permutation-algorithm
 -}

main = putStrLn ans
ans = case (iterate (>>= nextPerm) (Just [0..9])) !! (10^6 - 1)
      of Just digits -> concatToString digits  -- Extract from Just wrapper

nextPerm :: [Int] -> Maybe [Int]
nextPerm [] = Nothing
nextPerm xs =
	let
		len = length xs
		revSuffix = findPrefix (reverse xs)  -- Reverse of longest non-increasing suffix
		suffixLen = length revSuffix
		prefixMinusPivot = take (len - suffixLen - 1) xs
		pivot = xs !! (len - suffixLen - 1)
		suffixHead = takeWhile (<= pivot) revSuffix
		newPivot : suffixTail = drop (length suffixHead) revSuffix
		newSuffix = suffixHead ++ (pivot : suffixTail)
	in
		if suffixLen == len then Nothing else Just (prefixMinusPivot ++ (newPivot : newSuffix))
	where
		findPrefix [] = []
		findPrefix (x:xs) = x : (if xs /= [] && x <= (head xs) then (findPrefix xs) else [])

concatToString :: [Int] -> String
concatToString xs = foldl (++) "" (map show xs)
