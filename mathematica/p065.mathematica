(* 
 * Solution to Project Euler problem 65
 * by Project Nayuki
 * 
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Use this in Wolfram Mathematica *)

Total[IntegerDigits[Numerator[FromContinuedFraction[ContinuedFraction[E, 100]]]]]
