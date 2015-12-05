(* 
 * Solution to Project Euler problem 47
 * by Project Nayuki
 * 
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Use this in Wolfram Mathematica *)

Has4PrimeFactors[n_] := Length[FactorInteger[n]] == 4
i = 2;
While[!(Has4PrimeFactors[i] && Has4PrimeFactors[i+1] && Has4PrimeFactors[i+2] && Has4PrimeFactors[i+3]), i++]
i
