(* 
 * Solution to Project Euler problem 21
 * by Project Nayuki
 * 
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Use this in Wolfram Mathematica *)

DivisorSum[n_] := DivisorSigma[1, n] - n
AmicableQ[n_] := DivisorSum[n] != n && DivisorSum[DivisorSum[n]] == n
Total[Select[Range[9999], AmicableQ]]
