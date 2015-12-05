(* 
 * Solution to Project Euler problem 37
 * by Project Nayuki
 * 
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Use this in Wolfram Mathematica *)

TruncatablePrimeQ[p_] := Block[{n = Floor[Log[10, p]]},
  Apply[And, Table[PrimeQ[Floor[p/10^i]] && PrimeQ[Mod[p, 10^(i + 1)]], {i, 0, n}]]]

s = 0;
For[i = 5; c = 0, c < 11, i++,
  If[TruncatablePrimeQ[Prime[i]],
    s += Prime[i];
    c++]]
s
