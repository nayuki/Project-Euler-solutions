(* 
 * Solution to Project Euler problem 33
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* 
 * Consider an arbitrary fraction n/d:
 *   Let n = 10 * n1 + n0 be the numerator.
 *   Let d = 10 * d1 + d0 be the denominator.
 * As stated in the problem, we need 10 <= n < d < 100.
 * We must disregard trivial simplifications where n0 = d0 = 0.
 * 
 * Now, a simplification with n0 = d0 is impossible because:
 *   n1 / d1 = n / d = (10*n1 + n0) / (10*d1 + n0).
 *   n1 * (10*d1 + n0) = d1 * (10*n1 + n0).
 *   10*n1*d1 + n1*n0 = 10*d1*n1 + d1*n0.
 *   n1*n0 = d1*n0.
 *   n1 = d1.
 *   This implies n = d, which contradicts the fact that n < d.
 * Similarly, we cannot have a simplification with n1 = d1 for the same reason.
 * 
 * Therefore we only need to consider the cases where n0 = d1 or n1 = d0.
 * In the first case, check that n1/d0 = n/d;
 * in the second case, check that n0/d1 = n/d.
 *)
CanSimplify[n_, d_] :=
  10 <= n < d <= 99 &&
  Block[{n0 = Mod[n, 10], n1 = Floor[n / 10],
         d0 = Mod[d, 10], d1 = Floor[d / 10]},
    d0 != 0 && n0 == d1 && n1/d0 == n/d || n1 == d0 && n0/d1 == n/d]

prod = 1;
For[d = 10, d < 100, d++,
  For[n = 10, n < d, n++,
    If[CanSimplify[n, d], prod *= n / d]]]
Denominator[prod]
