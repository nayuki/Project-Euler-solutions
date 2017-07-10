{- 
 - Solution to Project Euler problem 33
 - Copyright (c) Project Nayuki. All rights reserved.
 - 
 - https://www.nayuki.io/page/project-euler-solutions
 - https://github.com/nayuki/Project-Euler-solutions
 -}

import Data.Ratio ((%), denominator)


{- 
 - Consider an arbitrary fraction n/d:
 -   Let n = 10 * n1 + n0 be the numerator.
 -   Let d = 10 * d1 + d0 be the denominator.
 - As stated in the problem, we need 10 <= n < d < 100.
 - We must disregard trivial simplifications where n0 = d0 = 0.
 - 
 - Now, a simplification with n0 = d0 is impossible because:
 -   n1 / d1 = n / d = (10*n1 + n0) / (10*d1 + n0).
 -   n1 * (10*d1 + n0) = d1 * (10*n1 + n0).
 -   10*n1*d1 + n1*n0 = 10*d1*n1 + d1*n0.
 -   n1*n0 = d1*n0.
 -   n1 = d1.
 -   This implies n = d, which contradicts the fact that n < d.
 - Similarly, we cannot have a simplification with n1 = d1 for the same reason.
 - 
 - Therefore we only need to consider the cases where n0 = d1 or n1 = d0.
 - In the first case, check that n1/d0 = n/d;
 - in the second case, check that n0/d1 = n/d.
 -}
main = putStrLn (show ans)
ans = denominator (product (map (\(n,d) -> (n % d)) candidates))
searchRange = [(numer, denom) | denom <- [10..99], numer <- [10..denom-1]]
candidates = filter isDcf searchRange

-- Is it a digit-cancelling fraction?
isDcf :: (Int, Int) -> Bool
isDcf (numer, denom) = let
		n0 = mod numer 10
		n1 = div numer 10
		d0 = mod denom 10
		d1 = div denom 10
	in (n1 == d0 && n0 * denom == numer * d1) || (n0 == d1 && n1 * denom == numer * d0)
