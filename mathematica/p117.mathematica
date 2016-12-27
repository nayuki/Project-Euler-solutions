(*
 * Solution to Project Euler problem 117
 * Copyright (c) Project Nayuki. All rights reserved.
 *
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


Ways[0] = 1;
Ways[n_] := Ways[n] = Sum[Ways[k], {k, Max[n - 4, 0], n - 1}]  (* Memoization *)
Ways[50]
