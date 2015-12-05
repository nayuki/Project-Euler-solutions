(* 
 * Solution to Project Euler problem 9
 * by Project Nayuki
 * 
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Use this in Wolfram Mathematica *)

For[a = 1, a <= 1000, a++,
  For[b = a, b <= 1000, b++,
    Block[{c = 1000 - a - b},
      If[a <= b <= c && a^2 + b^2 == c^2,
        Print[a, " ", b, " ", c, " ", a * b * c]]]]]
