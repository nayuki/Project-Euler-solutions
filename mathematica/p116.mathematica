(*
 * Solution to Project Euler problem 116
 * Copyright (c) Project Nayuki. All rights reserved.
 *
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


len = 50;
Ways[_, 0] = 1;
Ways[m_, n_] := Ways[m, n] = Ways[m, n - 1] + If[n >= m, Ways[m, n - m], 0]  (* Memoization *)
Sum[Ways[m, len] - 1, {m, 2, 4}]
