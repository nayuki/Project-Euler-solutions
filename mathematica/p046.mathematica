(* 
 * Solution to Project Euler problem 46
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


Goldbach[n_] := PrimeQ[n] || Block[{i},
  For[i = 1, Prime[i] < n && !IntegerQ[Sqrt[(n - Prime[i]) / 2]], i++]; Prime[i] < n]

i = 3;
While[Goldbach[i], i += 2]
i
