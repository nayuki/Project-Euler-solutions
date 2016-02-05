(* 
 * Solution to Project Euler problem 12
 * by Project Nayuki
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


TriangleNumber[n_] = Sum[i, {i, n}];
i = 1;
While[DivisorSigma[0, TriangleNumber[i]] <= 500, i++]
TriangleNumber[i]
