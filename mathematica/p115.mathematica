(*
 * Solution to Project Euler problem 115
 * by Project Nayuki
 *
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Use this in Wolfram Mathematica *)

m = 50;
Ways[0] = 1;
Ways[n_] := Ways[n] = Ways[n - 1] + If[n >= m, 1 + Sum[Ways[k], {k, 0, n - m - 1}], 0]  (* Memoization *)
i = 0;
While[Ways[i] <= 10^6, i++]
i
