(* 
 * Solution to Project Euler problem 187
 * by Project Nayuki
 * 
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Use this in Wolfram Mathematica *)

SemiprimeQ[n_] := Total[Map[Function[a, a[[2]]], FactorInteger[n]]] == 2
s = 0;
For[i = 1, i < 10^8, i++,
  If[SemiprimeQ[i],
    s++]]
s
