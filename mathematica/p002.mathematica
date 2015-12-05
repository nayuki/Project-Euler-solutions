(* 
 * Solution to Project Euler problem 2
 * by Project Nayuki
 * 
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Use this in Wolfram Mathematica *)

s = 0;
For[i = 0, (f = Fibonacci[i]) <= 4000000, i++,
  If[EvenQ[f],
    s += f]]
s