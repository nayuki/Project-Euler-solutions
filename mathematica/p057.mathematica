(* 
 * Solution to Project Euler problem 57
 * by Project Nayuki
 * 
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Use this in Wolfram Mathematica *)

CountDigits[n_] := Length[IntegerDigits[n]]
ContFrac[n_] := FromContinuedFraction[ContinuedFraction[Sqrt[2], n]]
NumExceedDenomQ[n_] := CountDigits[Numerator[ContFrac[n]]] > CountDigits[Denominator[ContFrac[n]]]
Length[Select[Range[2, 1001], NumExceedDenomQ]]
