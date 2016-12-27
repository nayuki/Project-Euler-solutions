(*
 * Solution to Project Euler problem 191
 * Copyright (c) Project Nayuki. All rights reserved.
 *
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


numDays = 30;
maxAbsent = 2;
maxLate = 1;

NumPrizeStrings[0, 0, 0] = 1;
NumPrizeStrings[0, _, _] = 0;
(* Memoization *)
NumPrizeStrings[d_, l_, 0 ] := NumPrizeStrings[d, l, 0] = Sum[NumPrizeStrings[d - 1, l, a], {a, 0, maxAbsent}] + If[l > 0, Sum[NumPrizeStrings[d - 1, l - 1, a], {a, 0, maxAbsent}], 0]
NumPrizeStrings[d_, l_, a_] := NumPrizeStrings[d, l, a] = NumPrizeStrings[d - 1, l, a - 1]

Sum[NumPrizeStrings[numDays, l, a], {l, 0, maxLate}, {a, 0, maxAbsent}]
