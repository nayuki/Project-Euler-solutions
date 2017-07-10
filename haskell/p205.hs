{- 
 - Solution to Project Euler problem 205
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import Data.Ratio ((%))
import Numeric (showFFloat)


main = putStrLn (ans "")
numer = sum [(ninePyramidalPdf !! i) * (sum (take i sixCubicPdf)) | i <- [0 .. ((length ninePyramidalPdf) - 1)]]
denom = (sum ninePyramidalPdf) * (sum sixCubicPdf)
ans = showFFloat (Just 7) (fromRational (numer % denom))


pyramidalDiePdf = [0, 1, 1, 1, 1] :: [Integer]
ninePyramidalPdf = (iterate (convolve pyramidalDiePdf) [1]) !! 9

cubicDiePdf = [0, 1, 1, 1, 1, 1, 1] :: [Integer]
sixCubicPdf = (iterate (convolve cubicDiePdf) [1]) !! 6


convolve :: [Integer] -> [Integer] -> [Integer]
convolve as bs = let
		m = length as
		n = length bs
		func i = sum [(as !! j) * (bs !! (i - j)) | j <- [(max 0 (i - (n - 1))) .. (min i (m - 1))]]
	in map func [0 .. (m + n - 2)]
