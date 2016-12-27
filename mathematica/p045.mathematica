(* 
 * Solution to Project Euler problem 45
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


n = 1;
While[temp = Intersection[Table[k(k+1)/2, {k, n}], Table[k(3k-1)/2, {k, n}], Table[k(2k-1), {k, n}]];
  Max[temp] <= 40755, n *= 2]
Min[Select[temp, Function[x, x > 40755]]]
