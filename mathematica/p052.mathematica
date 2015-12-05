(* 
 * Solution to Project Euler problem 52
 * by Project Nayuki
 * 
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Use this in Wolfram Mathematica *)

SameDigits[x_, y_] := Sort[IntegerDigits[x]] == Sort[IntegerDigits[y]]
i = 1;
While[!(SameDigits[i, 2i] && SameDigits[i, 3i] && SameDigits[i, 4i] && SameDigits[i, 5i] && SameDigits[i, 6i]), i++]
i
