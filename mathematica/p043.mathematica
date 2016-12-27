(* 
 * Solution to Project Euler problem 43
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


NextPermutation[s_] := Block[{i, j},
  For[i = Length[s], i > 1 && s[[i - 1]] >= s[[i]], i--];
  If[i <= 1, Abort[]];
  For[j = Length[s], s[[j]] <= s[[i - 1]], j--];
  Join[Take[s, i - 2], {s[[j]]}, Reverse[Drop[ReplacePart[s, s[[i - 1]], j], i - 1]]]]

s = 0;
perm = Range[0, 9];
While[True,
  If[Apply[And, Table[Mod[FromDigits[Take[perm, {i + 1, i + 3}]], Prime[i]] == 0, {i, 7}]],
    s += FromDigits[perm]];
  perm = NextPermutation[perm];
  If[perm == Range[9, 0, -1],
    Break[]]]
s
