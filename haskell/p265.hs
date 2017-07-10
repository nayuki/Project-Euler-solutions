{- 
 - Solution to Project Euler problem 265
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import Data.Bits ((.&.), (.|.), shiftL, shiftR)
import Data.List (nub)


{- 
 - In this problem we look at 2^n-digit binary strings and the n-digit substrings of these.
 - We are given that n = 5, so we are looking at windows of 5 bits in 32-bit strings.
 - 
 - There are of course 32 possible cyclic windows in a 32-bit string.
 - We want each of these windows to be a unique 5-bit string. There are exactly 2^5 = 32
 - possible 5-bit strings, hence the 32 windows must cover the 5-bit space exactly once.
 - 
 - The result requires the substring of all zeros to be in the most significant bits.
 - We argue that the top n bits must be all zeros, because this is one of the cyclic windows
 - and the value 00...00 must occur once. Furthermore the next and previous bit must be 1 -
 - because if they're not, then at least one of the adjacent windows are also zero, which
 - violates the uniqueness requirement.
 - 
 - With n = 5, this means every candidate string must start with 000001 and end with 1.
 - In other words, they are of the form 000001xxxxxxxxxxxxxxxxxxxxxxxxx1.
 - The middle 25 bits still need to be determined, and we simply search by brute force.
 -}

n = 5 :: Int  -- Must be at least 1
twoPowN = (2^n) :: Int
mask = toInteger (twoPowN - 1)  -- Equal to n 1's in binary, i.e. 0b11111
start = 2^(toInteger (twoPowN - n - 1)) + 1
end = 2^(toInteger (twoPowN - n)) - 1

main = putStrLn (show ans)
ans = sum (filter checkArrangement [start .. end])


checkArrangement :: Integer -> Bool
checkArrangement digits = let
		dig = digits .|. (shiftL digits twoPowN)  -- Make second copy
		rots = [(shiftR dig i) .&. mask | i <- [0 .. (twoPowN - 1)]]
	in (length (nub rots)) == twoPowN
