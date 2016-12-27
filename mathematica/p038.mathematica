(*
 * Solution to Project Euler problem 38
 * Copyright (c) Project Nayuki. All rights reserved.
 *
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


PandigitalQ[x_] := DigitCount[x] == {1, 1, 1, 1, 1, 1, 1, 1, 1, 0}
Max[
  Select[
    Flatten[Table[
      FromDigits[Flatten[Table[IntegerDigits[i * k], {k, 1, n}]]],
      {n, 2, 9}, {i, 1, 10^Floor[9 / n] - 1}]],
    PandigitalQ]]
