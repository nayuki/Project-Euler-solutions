(* 
 * Solution to Project Euler problem 160
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


modulus = 10^5;
F [n_] := Mod[G[n] * PowerMod[2, CF[n, 2] - CF[n, 5], modulus], modulus]
G [n_] := Mod[GE[n] * GO[n], modulus]
GE[0 ]  = 1;
GE[n_] := G[Floor[n / 2]]
GO[0 ]  = 1;
GO[n_] := Mod[GO[Floor[n / 5]] * H[n], modulus]
H [n_] := Apply[Times, Select[Range[Max[Mod[n, modulus], 1]], Function[k, GCD[k, 10] == 1]]]
CF[0 , _ ]  = 0;
CF[m_, n_] := Floor[m / n] + CF[Floor[m / n], n]
F[10^12]
