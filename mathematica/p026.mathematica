(* 
 * Solution to Project Euler problem 26
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


CycleLength[x_] := Length[Last[First[RealDigits[x]]]]
d = 1;
For[i = 1, i < 1000, i++,
  If[CycleLength[1 / i] > CycleLength[1 / d], d = i]]
d
