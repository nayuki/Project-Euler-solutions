(* 
 * Solution to Project Euler problem 85
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


target = 2000000;

NumRect[m_, n_] := (m + 1) * m * (n + 1) * n / 4  (* A bit more than m^2 n^2 / 4 *)

bestDiff = -1;
bestArea = -1;
lim = Floor[Sqrt[target]];
For[w = 1, w <= lim, w++,
	For[h = 1, h <= lim, h++,
		diff = Abs[NumRect[w, h] - target];
		If[bestDiff == -1 || diff < bestDiff,
			bestDiff = diff;
			bestArea = w * h;]]]
bestArea
