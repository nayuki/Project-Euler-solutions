{- 
 - Solution to Project Euler problem 24
 - by Project Nayuki
 - 
 - https://www.nayuki.io/page/project-euler-solutions
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
		suffixHead = takeWhile (<= pivot) revSuffix
		newPivot : suffixTail = drop (length suffixHead) revSuffix
		newSuffix = suffixHead ++ (pivot : suffixTail)
	in
		if suffixLen == len then Nothing else Just (prefixMinusPivot ++ (newPivot : newSuffix))
	where
		findPrefix [] = []
		findPrefix (x:xs) = x : (if xs /= [] && x <= (head xs) then (findPrefix xs) else [])

digitsToNum acc [] = acc
digitsToNum acc (x:xs) = digitsToNum (acc * 10 + x) xs
