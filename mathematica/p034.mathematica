(* 
 * Solution to Project Euler problem 34
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Each candidate number is less than 10^7 because even the largest 8-digit number, 9999999, yields a sum of only 8 * 9! = 2903040, which is 7 digits. *)
Total[Select[Range[3, 10^7], Function[n, n == Total[Map[Factorial, IntegerDigits[n]]]]]]
