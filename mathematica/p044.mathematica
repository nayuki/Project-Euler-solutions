(* 
 * Solution to Project Euler problem 44
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


PentaNumber[x_] := x * (x * 3 - 1) / 2
PentaNumberQ[y_] := y > 0 && Block[{z = Sqrt[y * 24 + 1]}, IntegerQ[z] && Mod[z, 6] == 5]

minD = -1;  (* -1 means not found yet, positive number means found a candidate *)
(* For each upper pentagonal number index, going upward *)
For[i = 2, True, i++,
  pentI = PentaNumber[i];
  (* If the next number down is at least as big as a found difference, then conclude searching *)
  If[minD != -1 && pentI - PentaNumber[i - 1] >= minD,
    Break[]];
  (* For each lower pentagonal number index, going downward *)
  For[j = i - 1, j >= 1, j--,
    pentJ = PentaNumber[j];
    diff = pentI - pentJ;
    (* If the difference is at least as big as a found difference, then stop testing lower pentagonal numbers *)
    If[minD != -1 && diff >= minD,
      Break[]];
    If[PentaNumberQ[pentI + pentJ] && PentaNumberQ[diff],
      minD = diff]]]
minD
