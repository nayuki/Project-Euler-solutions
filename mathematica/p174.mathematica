(* 
 * Solution to Project Euler problem 174
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


sizeLimit = 10^6;
typeLimit = 10;

type = Table[0, {sizeLimit}];
For[n = 3, (n - 1) * 4 <= sizeLimit, n++,  (* Outer square size *)
  For[m = n - 2, m >= 1, m -= 2,  (* Inner square hole size *)
    tiles = n^2 - m^2;
    If[tiles > sizeLimit,
      Break[],
      type[[tiles]]++]]]
Length[Select[type, Function[x, 1 <= x <= typeLimit]]]
