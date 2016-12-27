(* 
 * Solution to Project Euler problem 171
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* 
 * The key insight is to use dynamic programming to build up the answer one digit at a time.
 * 
 * Let Num(n, s) denote the set of numbers of length n (with leading zeros) whose squared digits sum to s.
 * For example, Num(2, 25) = {05, 34, 43, 50}.
 * Then for any particular n and s, we know that Num(n, s) = union of
 *   (prepend 0 to each of Num(n-1, s - 0*0)),
 *   (prepend 1 to each of Num(n-1, s - 1*1)),
 *   ...,
 *   (prepend 9 to each of Num(n-1, s - 9*9)).
 * 
 * However, keeping track of these sets of numbers explicitly is just as slow as iterating over
 * all the numbers by brute force. So instead, we only store the sums and counts of these sets,
 * and these two pieces of information are enough to determine the final answer.
 * (Furthermore, these can be reduced by the modulus.)
 *)
len = 20;
base = 10;
md = 10^9;

(* Memoization *)
NumSum[n_, s_] := NumSum[n, s] = If[n == 0, 0, If[s >= 0, Sum[NumSum[n - 1, s - k^2] + k * base^(n - 1) * NumCount[n - 1, s - k^2], {k, 0, base - 1}], 0]]
NumCount[n_, s_] := NumCount[n, s] = If[n == 0, Boole[s == 0], If[s >= 0, Sum[NumCount[n - 1, s - k^2], {k, 0, base - 1}], 0]]

Mod[Sum[NumSum[len, k^2], {k, Floor[Sqrt[(base - 1)^2 * len]]}], md]
