(* 
 * Solution to Project Euler problem 203
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


SquareFreeQ[n_] := Length[Select[FactorInteger[n], Function[x, x[[2]] > 1]]] == 0
Total[Select[Union[Flatten[Table[Binomial[n, k], {n, 0, 50}, {k, 0, n}]]], SquareFreeQ]]
