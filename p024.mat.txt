(* 
 * Solution to Project Euler problem 24
 * by Project Nayuki
 * 
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Use this in Wolfram Mathematica *)

NextPermutation[s_] := Block[{i, j},
  (* Find non-increasing suffix. e.g.: 1 3 [5 4 4 2] *)
  For[i = Length[s], i > 1 && s[[i - 1]] >= s[[i]], i--];
  (* i is the index of the head of such suffix *)
  If[i <= 1, Abort[]];
  (* Find latest element that exceeds s[i - 1] *)
  For[j = Length[s], s[[j]] <= s[[i - 1]], j--];
  (* Return new list with indexes i and j swapped, followed by the suffix reversed *)
  Join[Take[s, i - 2], {s[[j]]}, Reverse[Drop[ReplacePart[s, s[[i - 1]], j], i - 1]]]]

FromDigits[Nest[NextPermutation, Range[0, 9], 10^6 - 1]]
