(* 
 * Solution to Project Euler problem 429
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


num = 10^8;
md = 1000000009;

CountFactors[n_, p_] := If[n == 0, 0, Floor[n / p] + CountFactors[Floor[n / p], p]]

s = 1;
For[i = 1, Prime[i] <= num, i++,
  s = Mod[(PowerMod[Prime[i], CountFactors[num, Prime[i]] * 2, md] + 1) * s, md]]
s
