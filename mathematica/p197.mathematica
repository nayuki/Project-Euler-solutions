(* 
 * Solution to Project Euler problem 197
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


iterations = 10^12;
F[x_] := Floor[2^(30403243784/10^9 - x^2)] / 10^9

(* Floyd's cycle-finding algorithm *)
x = -1;
y = -1;
i = 0;
While[i < iterations,
  x = F[x];
  y = F[F[y]];
  i++;
  If[x == y, Break[]]]

(* Deal with remaining iterations *)
remain = Mod[iterations - i, i];
While[remain > 0, x = F[x]; remain--]
answer = x + F[x];
N[Floor[answer * 10^9] / 10^9, Floor[Log[10, answer]] + 1 + 9]
