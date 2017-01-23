(* 
 * Solution to Project Euler problem 21
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* 
 * We modify a built-in function to find the sum of proper divisors of a number.
 * Then we apply the definition of an amicable number straightforwardly.
 *)
DivisorSum[n_] := DivisorSigma[1, n] - n
AmicableQ[n_] := DivisorSum[n] != n && DivisorSum[DivisorSum[n]] == n
Total[Select[Range[9999], AmicableQ]]
