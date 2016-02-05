(* 
 * Solution to Project Euler problem 35
 * by Project Nayuki
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


RotateInt[n_, k_] := FromDigits[RotateLeft[IntegerDigits[n], i]]
CircularPrimeQ[n_] := Apply[And, Table[PrimeQ[RotateInt[n, i]], {i, Length[IntegerDigits[n]]}]]
Length[Select[Range[2, 999999], CircularPrimeQ]]
