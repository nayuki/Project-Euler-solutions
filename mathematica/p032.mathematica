(* 
 * Solution to Project Euler problem 32
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


HasPandigitalProduct[n_] := Select[Divisors[n], Function[k, DigitCount[n] + DigitCount[k] + DigitCount[n/k] == {1, 1, 1, 1, 1, 1, 1, 1, 1, 0}]] != {}

(* A candidate has at most 4 digits. This is because if it has 4 digits, then expressing it as a product of two numbers uses at least 4 digits. *)
s = 0;
For[i = 1, i < 10^4, i++,
  If[HasPandigitalProduct[i],
    s += i]]
s
