(* 
 * Solution to Project Euler problem 203
 * by Project Nayuki
 * 
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Use this in Wolfram Mathematica *)

SquareFreeQ[n_] := Length[Select[FactorInteger[n], Function[x, x[[2]] > 1]]] == 0
Total[Select[Union[Flatten[Table[Binomial[n, k], {n, 0, 50}, {k, 0, n}]]], SquareFreeQ]]
