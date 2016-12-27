(* 
 * Solution to Project Euler problem 35
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


RotateInt[n_, k_] := FromDigits[RotateLeft[IntegerDigits[n], i]]
CircularPrimeQ[n_] := Apply[And, Table[PrimeQ[RotateInt[n, i]], {i, Length[IntegerDigits[n]]}]]
Length[Select[Range[2, 999999], CircularPrimeQ]]
