(* 
 * Solution to Project Euler problem 205
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


DiceProbs[sides_, dice_] := CoefficientList[(Sum[x^i, {i, 1, sides}] / sides)^dice, x];
pyramidal = DiceProbs[4, 9];
cubic = DiceProbs[6, 6];
N[Sum[pyramidal[[i + 1]] * cubic[[j + 1]], {i, 0, 36}, {j, 0, i - 1}], 7]
