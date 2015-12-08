(* 
 * Solution to Project Euler problem 40
 * by Project Nayuki
 * 
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Use this in Wolfram Mathematica *)

str = Apply[Join, Table[IntegerDigits[n], {n, 10^6}]];
Product[str[[10^i]], {i, 0, 6}]
