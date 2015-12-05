(* 
 * Solution to Project Euler problem 12
 * by Project Nayuki
 * 
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Use this in Wolfram Mathematica *)

TriangleNumber[n_] = Sum[i, {i, n}];
i = 1;
While[DivisorSigma[0, TriangleNumber[i]] <= 500, i++]
TriangleNumber[i]
