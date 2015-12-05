(*
 * Solution to Project Euler problem 114
 * by Project Nayuki
 *
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Use this in Wolfram Mathematica *)

Ways[n_] := Ways[n] = If[n < 3, 1, Ways[n - 1] + 1 + Sum[Ways[k], {k, 0, n - 4}]]  (* Memoization *)
Ways[50]
