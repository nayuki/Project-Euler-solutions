(* 
 * Solution to Project Euler problem 250
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


numSubsets = Table[Boole[i == 250], {i, 250}];
For[i = 1, i <= 250250, i++,
  Block[{temp = PowerMod[i, i, 250]},
    numSubsets = Table[Mod[numSubsets[[j]] + numSubsets[[Mod[j - temp, 250, 1]]], 10^16], {j, 250}]]]
Mod[numSubsets[[250]] - 1, 10^16]
