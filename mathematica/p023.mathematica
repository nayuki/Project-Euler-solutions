(* 
 * Solution to Project Euler problem 23
 * by Project Nayuki
 * 
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


lim = 28123;

isAbundant = Table[DivisorSigma[1, n] - n > n, {n, lim}];
abundants = Pick[Range[lim], isAbundant];
SumOfTwoAbundantsQ[n_] := Block[{i},
  For[i = 1, i < Length[abundants] && abundants[[i]] < n, i++,
    If[isAbundant[[n - abundants[[i]]]],
      Return[True]]]; False]

Total[Select[Range[lim], (!SumOfTwoAbundantsQ[#])&]]
