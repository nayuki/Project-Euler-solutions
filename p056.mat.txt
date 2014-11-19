(* 
 * Solution to Project Euler problem 56
 * by Project Nayuki
 * 
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Use this in Wolfram Mathematica *)

Max[Flatten[Table[Total[IntegerDigits[a^b]], {a, 100}, {b, 100}]]]
