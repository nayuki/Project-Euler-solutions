(* 
 * Solution to Project Euler problem 12
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* We do a straightforward search with some help from built-in functions. *)
TriangleNumber[n_] = Sum[i, {i, n}];
i = 1;
While[DivisorSigma[0, TriangleNumber[i]] <= 500, i++]
TriangleNumber[i]
