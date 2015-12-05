(* 
 * Solution to Project Euler problem 1
 * by Project Nayuki
 * 
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Use this in Wolfram Mathematica *)

Total[Select[Range[999], Function[x, Mod[x, 3] == 0 || Mod[x, 5] == 0]]]