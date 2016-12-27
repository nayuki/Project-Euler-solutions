(* 
 * Solution to Project Euler problem 41
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


PrevPermutation[s_] := Block[{i, j},
  (* Find non-decreasing suffix. e.g.: 1 3 [2 2 4 5] *)
  For[i = Length[s], i > 1 && s[[i - 1]] <= s[[i]], i--];
  (* i is the index of the head of such suffix *)
  If[i <= 1, Abort[]];
  (* Find latest element that is less than s[i - 1] *)
  For[j = Length[s], s[[j]] >= s[[i - 1]], j--];
  (* Return new list with indexes i and j swapped, followed by the suffix reversed *)
  Join[Take[s, i - 2], {s[[j]]}, Reverse[Drop[ReplacePart[s, s[[i - 1]], j], i - 1]]]]

ans = "Not found";
For[n = 9, n >= 1, n--,
  perm = Range[n, 1, -1];
  While[True,
    If[PrimeQ[FromDigits[perm]],
      ans = FromDigits[perm];
      n = 0;
      Break[]];
    If[perm == Range[n], Break[]];
    perm = PrevPermutation[perm];]]
ans
