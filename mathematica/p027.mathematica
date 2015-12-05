(* 
 * Solution to Project Euler problem 27
 * by Project Nayuki
 * 
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Use this in Wolfram Mathematica *)

CountConsecutivePrimes[a_, b_] := Block[{i}, For[i = 0, PrimeQ[i^2 + a * i + b], i++]; i]

maxConsecutive = 0;
maxProduct = 0;
For[a = -999, a < 1000, a++,
  For[b = -999, b < 1000, b++,
    temp = CountConsecutivePrimes[a, b];
    If[temp > maxConsecutive,
      maxConsecutive = temp;
      maxProduct = a * b]]]
maxProduct
