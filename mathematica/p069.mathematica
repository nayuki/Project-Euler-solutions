(* 
 * Solution to Project Euler problem 69
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


maxVal = 0;
maxArg = 0;
For[n = 1, n <= 10^6, n++,
  Block[{val = n / EulerPhi[n]},
    If[val > maxVal,
      maxVal = val;
      maxArg = n]]]
maxArg
