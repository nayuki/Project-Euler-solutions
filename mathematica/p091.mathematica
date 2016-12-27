(* 
 * Solution to Project Euler problem 91
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


n = 50;

(* Tests whether {(0,0), (x1,y1), (x2,y2)} forms a right triangle *)
RightTriangleQ[x1_, y1_, x2_, y2_] :=
  Block[{dx = x2 - x1, dy = y2 - y1},
    Block[{a = x1^2 + y1^2, b = x2^2 + y2^2, c = dx^2 + dy^2},
      a + b == c || b + c == a || c + a == b]]

(* For uniqueness, ensure that (x1,y1) has a larger angle than (x2,y2) *)
temp = Table[Boole[y2 * x1 < y1 * x2 && RightTriangleQ[x1, y1, x2, y2]],
  {x1, 0, n}, {y1, 0, n}, {x2, 0, n}, {y2, 0, n}];
Total[Flatten[temp]]
