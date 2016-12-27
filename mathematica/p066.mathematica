(* 
 * Solution to Project Euler problem 66
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* 
 * Based on an insane theorem about truncating the last term of the periodic continued fraction
 * of sqrt(n) to get the minimum solution to the Pell's equation x^2 - n y^2 = 1.
 *)
MinSolutionX[n_] := If[IntegerQ[Sqrt[n]], 0,
  Block[{w = ContinuedFraction[Sqrt[n]]},
    Block[{z = FromContinuedFraction[Join[Most[w], Most[Last[w]]]]},
      If[Mod[Length[Last[w]], 2] == 0, Numerator[z], Numerator[z]^2 + Denominator[z]^2]]]]
minXs = Map[MinSolutionX, Range[1000]];
Position[minXs, Max[minXs]][[1, 1]]
