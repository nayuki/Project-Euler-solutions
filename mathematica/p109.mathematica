(* 
 * Solution to Project Euler problem 109
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Both lists are orderless but duplicates are important; they are sort of like multisets *)
points = Join[Flatten[Table[i * j, {i, 20}, {j, 3}]], {25, 50}];
doublePoints = Join[Table[i * 2, {i, 20}], {25 * 2}];

Ways[throws_, total_, maxIndex_] := Ways[throws, total, maxIndex] =  (* Memoization *)
  If[throws == 0,
    Boole[total == 0],
    If[maxIndex > 1, Ways[throws, total, maxIndex - 1], 0] +
      If[points[[maxIndex]] <= total, Ways[throws - 1, total - points[[maxIndex]], maxIndex], 0]]

Sum[
  If[doublePoints[[i]] <= remainingPoints,
    Ways[throws, remainingPoints - doublePoints[[i]], Length[points]],
    0],
  {remainingPoints, 99},
  {throws, 0, 2},
  {i, Length[doublePoints]}]
