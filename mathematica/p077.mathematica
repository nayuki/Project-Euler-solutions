(* 
 * Solution to Project Euler problem 77
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* 
 * Let P(i, n) denote the number of ways that n can be written as an
 * unordered sum of prime numbers where no prime is greater than i.
 * 
 * - P(i, 0) = 1 for all i.
 * - P(0, n) = 0 for all n > 0.
 * - If i is 1 or composite then P(i, n) = P(i - 1, n).
 * - Otherwise i is prime:
 *   - If i <= n then P(i, n) = P(i - 1, n) + P(i, n - i).
 *   - Else P(i, n) = P(i - 1, n).
 *)
P[_, 0] = 1;
P[0, _] = 0;
P[i_, n_] := P[i, n] = If[PrimeQ[i] && i <= n, P[i, n - i], 0] + P[i - 1, n]  (* Memoization *)

i = 2;
While[P[i, i] <= 5000, i++]
i
