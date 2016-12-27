(* 
 * Solution to Project Euler problem 64
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


Length[Select[Range[10000], Function[x, !IntegerQ[Sqrt[x]] && OddQ[Length[Last[ContinuedFraction[Sqrt[x]]]]]]]]
