{- 
 - Solution to Project Euler problem 24
 - By Nayuki Minase
 - 
 - http://nayuki.eigenstate.org/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}


main = putStrLn (show ans)
ans = case (iterate (>>= nextPerm) (Just [0..9])) !! (10^6 - 1)
      of Just digits -> digitsToNum 0 digits  -- Extract from Just wrapper

nextPerm :: [Integer] -> Maybe [Integer]
nextPerm [] = Nothing
nextPerm xs =
	let
		len = length xs
		revSuffix = findPrefix (reverse xs)  -- Reverse of longest non-increasing suffix
		suffixLen = length revSuffix
		prefixMinusPivot = take (len - suffixLen - 1) xs
		pivot = xs !! (len - suffixLen - 1)
		tempIndex = (length (takeWhile (<= pivot) revSuffix))  -- Index of new pivot in reversed suffix
		newSuffix = (take tempIndex revSuffix) ++ (pivot : (drop (tempIndex + 1) revSuffix))
	in
		if suffixLen == len then Nothing else Just (prefixMinusPivot ++ ((revSuffix !! tempIndex) : newSuffix))
	where
		findPrefix [] = []
		findPrefix (x:xs) = x : (if xs /= [] && x <= (head xs) then (findPrefix xs) else [])

digitsToNum acc [] = acc
digitsToNum acc (x:xs) = digitsToNum (acc * 10 + x) xs
