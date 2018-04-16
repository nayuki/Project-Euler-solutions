(* 
 * Solution to Project Euler problem 587
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


LSectionArea = 1 - Pi / 4;

(* The indefinite integral of (1 - sqrt(2x - x^2)) dx. *)
TheIntegral[x_] := Block[{t = x - 1},
  t - (Sqrt[1 - t^2] * t + ArcSin[t]) / 2]

ConcaveTriangleArea[i_] := Block[{
    slope = 1 / i,
    a = slope^2 + 1,
    b = -2 * (slope + 1),
    c = 1,
    x = (2 * c) / (-b + Sqrt[b^2 - 4 * a * c])},
  x * (1 - Sqrt[(-x + 2) * x]) / 2 + TheIntegral[1] - TheIntegral[x]]

i = 1;
While[ConcaveTriangleArea[i] / LSectionArea >= 1 / 1000,
  i++]
i
