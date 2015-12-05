(* 
 * Solution to Project Euler problem 34
 * by Project Nayuki
 * 
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Use this in Wolfram Mathematica *)

(* Each candidate number is less than 10^7 because even the largest 8-digit number, 9999999, yields a sum of only 8 * 9! = 2903040, which is 7 digits. *)
Total[Select[Range[3, 10^7], Function[n, n == Total[Map[Factorial, IntegerDigits[n]]]]]]
