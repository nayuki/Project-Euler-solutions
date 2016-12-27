(* 
 * Solution to Project Euler problem 173
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* 
 * Let n be the side length of the lamina, k be the side length of the hole, and T be the number of tiles we have.
 * 
 * The smallest possible lamina is a 3*3 square with a hole in the middle. The largest possible lamina consumes as many tiles as possible
 * in a thin square outline with a side length of floor(T/4) + 1. (The hole has side length floor(T/4) - 1.) Thus 3 <= n <= floor(T/4) + 1.
 * 
 * Since the lamina is symmetrical, the side length of the hole is even if and only if the side length of the lamina is even (same parity).
 * The smallest possible hole is 1*1 or 2*2. The largest possible hole has side length n - 2. Thus 1 <= k <= n - 2.
 * 
 * The number of tiles used in a lamina is n^2 - k^2, so we have the constraint that 0 < n^2 - k^2 <= T.
 * We can rearrange this inequality to get k^2 >= n^2 - T. For a given n:
 * - If n^2 - T <= 0, then this inequality is true for every k, so the set of valid k's is {1 or 2, ..., n - 2}.
 *   The size of this set is floor((n - 1) / 2).
 * - Otherwise, the inequality implies that k >= ceil(sqrt(n^2 - T)). The set of valid k's is as follows:
 *   {m, m+2, m+4, ..., n-2}, where m the lowest number such that m >= ceil(sqrt(n^2 - T)) and m has the same parity as n.
 *   The size of this set is floor((n - m) / 2).
 * 
 * Now all we have to do is sum up the sizes of the sets of allowed k's, for n in the range [3, floor(T/4)+1].
 *)
tiles = 10^6;
Sum[
  Floor[(n - If[n * n - tiles <= 0, 1, Ceiling[Sqrt[n * n - tiles]]]) / 2],
  {n, 3, Floor[tiles / 4] + 1}]
