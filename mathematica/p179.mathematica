(* 
 * Solution to Project Euler problem 179
 * by Project Nayuki
 * 
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Use this in Wolfram Mathematica *)

Length[Select[Range[2, 10^7 - 1], Function[n, DivisorSigma[0, n] == DivisorSigma[0, n + 1]]]]
