(* 
 * Solution to Project Euler problem 92
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


SquareDigitSum[x_] := Total[Map[(#^2)&, IntegerDigits[x]]]
EightyNineQ[1] = False;
EightyNineQ[89] = True;
EightyNineQ[x_] := Block[{y = EightyNineQ[SquareDigitSum[x]]},
  If[x < 10^5, EightyNineQ[x] = y, y]]  (* Selective memoization *)
Length[Select[Range[10^7 - 1], EightyNineQ]]
