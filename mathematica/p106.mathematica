(* 
 * Solution to Project Euler problem 106
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)

setSize = 12;
CatalanNum[n_] := Binomial[n * 2, n] / (n + 1)
Sum[Binomial[setSize, i * 2] * (Binomial[i * 2, i] / 2 - CatalanNum[i]),
  {i, 2, Floor[setSize / 2]}]
