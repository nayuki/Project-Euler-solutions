(* 
 * Solution to Project Euler problem 14
 * by Project Nayuki
 * 
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Use this in Wolfram Mathematica *)

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
