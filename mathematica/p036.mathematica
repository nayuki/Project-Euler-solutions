(* 
 * Solution to Project Euler problem 36
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


PalindromeQ[n_, b_] := IntegerDigits[n, b] == Reverse[IntegerDigits[n, b]]
Total[Select[Range[999999], Function[x, PalindromeQ[x, 2] && PalindromeQ[x, 10]]]]
