(* 
 * Solution to Project Euler problem 71
 * by Project Nayuki
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


Numerator[Max[Table[If[Mod[i, 7] != 0, Floor[3 / 7 * i] / i, 0], {i, 1000000}]]]
