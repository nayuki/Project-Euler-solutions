(* 
 * Solution to Project Euler problem 401
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* 
 * Consider the set of all integers from 1 to n, inclusive: {1, 2, ..., n}.
 * Now form the set of divisors for each number:
 *   1: {1}
 *   2: {1, 2}
 *   3: {1, 3}
 *   4: {1, 2, 4}
 *   5: {1, 5}
 *   6: {1, 2, 3, 6}
 *   et cetera until n.
 * Next consider the multiset union of all these sets of divisors.
 * 
 * We know that for a given integer k > 0, it occurs as a divisor in this multiset
 * exactly floor(n / k) times (we call this the "count"), which are namely the multiples of k.
 * So instead of considering each integer and summing its squared divisors, we can consider
 * each divisor from 1 to n and compute how much it contributes to the final sum, namely floor(n / k) * k^2.
 * 
 * A futher observation is that when k is large, the count factor of floor(n / k) does not change often.
 * (For example, for k from floor(n/2)+1 to n, this count is always 1.)
 * So we can calculate the squared divisor sum for many numbers at a time.
 * This is helpful for k > sqrt(n), and we can bring the run time from O(n) down to O(sqrt(n)).
 * 
 * For a given count of m = floor(n / k), which integer values of k yield this m?
 * By the definition of floor, m <= n/k, so mk <= n, and k <= n/m, thus k <= floor(n/m).
 * Also by definition, m > n/k - 1, so mk > n - k, and k(m + 1) > n, and k > n/(m+1), so k > floor(n/(m+1)).
 * Together, we have: floor(n / (m + 1)) < k <= floor(n / m).
 * 
 * Useful fact: (sum k^2 for k=1 to n) = n(n + 1)(2n + 1) / 6.
 *)
n = 10^15;
md = 10^9;

(* To work around bugs in Sum[Floor[...], ...] when the range is large *)
MySum[f_, start_, end_] := Block[{i, s = 0},
  For[i = start, i <= end, i++, s += f[i]]; s]

(* From s (exclusive) to e (inclusive) *)
SumSquares[s_, e_] := (e(e+1)(2e+1) - s(s+1)(2s+1)) / 6

splitCount = Floor[Sqrt[n]];
splitAt = Floor[n / (splitCount + 1)];
Mod[
  MySum[Function[k, k^2 * Floor[n / k]], 1, splitAt] +
  MySum[Function[m, SumSquares[Floor[n / (m + 1)], Floor[n / m]] * m], 1, splitCount],
  md]
