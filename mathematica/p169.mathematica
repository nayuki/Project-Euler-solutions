(* 
 * Solution to Project Euler problem 169
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


num = 10^25;
$RecursionLimit = Max[10 * Floor[Log[2, num]], 1000];

(* 
 * Ways[n, i, j] is the number of ways that the number n can be expressed as an unordered sum of powers of 2 such that
 * the highest possible power is 2^i, this term is used between 0 and j times, and all lower powers of 2 are used no more than 2 times.
 *)
Ways[n_, -1, _] = Boole[n == 0];
Ways[n_, i_, j_] := Ways[n, i, j] =  (* Memoization *)
  Ways[n, i - 1, 2] + If[j > 0 && 2^i <= n && n < 2^i * (j + 2), Ways[n - 2^i, i, j - 1], 0]

Ways[num, Floor[Log[2, num]], 2]
