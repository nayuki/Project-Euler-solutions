(* 
 * Solution to Project Euler problem 174
 * by Project Nayuki
 * 
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Use this in Wolfram Mathematica *)

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
