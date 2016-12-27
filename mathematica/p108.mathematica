(* 
 * Solution to Project Euler problem 108
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* 
 * Rewrite the equation with x = n+i, y = n+j, and manipulate it:
 *   1/n = 1/x + 1/y
 *       = 1/(n+i) + 1/(n+j)
 *       = (2n+i+j) / ((n+i)(n+j)).
 *   n(2n+i+j) = (n+i)(n+j).
 *   2n^2 + ni + nj = n^2 + ni + nj + ij.
 *   n^2 = ij.
 * Hence i and j are divisors of n^2. To ensure unique solutions,
 * we impose that x <= y, so i <= j. Also, i > 0, otherwise no j exists.
 * We have i <= j = n^2 / i, thus i^2 <= n^2. With i being positive, we get that i <= n.
 * Therefore the number of solutions for i is the number of divisors of n^2 in the range [1, n].
 * n^2 always has an odd number of divisors. One of them is n. As for the remainder of them, half of them are below n
 * and half of them are above n. So if n^2 has m divisors, then we want (m+1)/2 of them as solutions for i.
 *)

For[n = 1, (DivisorSigma[0, n^2] + 1) / 2 <= 1000, n++]
n
