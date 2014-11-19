(* 
 * Solution to Project Euler problem 64
 * by Project Nayuki
 * 
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Use this in Wolfram Mathematica *)

Length[Select[Range[10000], Function[x, !IntegerQ[Sqrt[x]] && OddQ[Length[Last[ContinuedFraction[Sqrt[x]]]]]]]]
