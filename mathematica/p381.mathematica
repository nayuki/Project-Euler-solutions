(* 
 * Solution to Project Euler problem 381
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* By Wilson's theorem, (p-1)! = -1 mod p if and only if p is prime. *)
S[p_] := Mod[Sum[-1 * Product[PowerMod[-j, -1, p], {j, k - 1}], {k, 5}], p]

s = 0;
For[i = 3, Prime[i] <= 10^8, i++, s += S[Prime[i]]]
s
