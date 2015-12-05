(*
 * Solution to Project Euler problem 116
 * by Project Nayuki
 *
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Use this in Wolfram Mathematica *)

len = 50;
Ways[_, 0] = 1;
Ways[m_, n_] := Ways[m, n] = Ways[m, n - 1] + If[n >= m, Ways[m, n - m], 0]  (* Memoization *)
Sum[Ways[m, len] - 1, {m, 2, 4}]
