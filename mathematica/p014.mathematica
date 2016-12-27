(* 
 * Solution to Project Euler problem 14
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* 
 * We compute the Collatz chain length for every integer in the range according to the iteration rule.
 * Also, we cache the Collatz value for small integer arguments to speed up the computation.
 *)

Collatz[0] := 0
Collatz[1] := 1
Collatz[n_] := Block[{res = Collatz[If[EvenQ[n], n / 2, n * 3 + 1]] + 1},
  If[n < 10^5, Collatz[n] = res, res]]  (* Selective memoization *)

$RecursionLimit = 1000;
maxArg = -1;
maxVal = -1;
For[i = 0, i <= 10^6, i++,
  If[Collatz[i] > maxVal,
    maxVal = Collatz[i];
    maxArg = i]]
maxArg
