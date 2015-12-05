(* 
 * Solution to Project Euler problem 112
 * by Project Nayuki
 * 
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Use this in Wolfram Mathematica *)

BouncyQ[n_] := Block[{d = IntegerDigits[n]},
  Block[{s = Sort[d]}, d != s && d != Reverse[s]]]

b = 0;
For[i = 1, True, i++,
  b += Boole[BouncyQ[i]];
  If[b / i == 99 / 100,
    Break[]]]
i
