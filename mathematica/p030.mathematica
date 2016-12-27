(* 
 * Solution to Project Euler problem 30
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Each candidate number has at most 6 digits. This is because with 7 digits, even the largest number 9999999 yields a sum of only 7 * 9^5 = 413343, which is 6 digits. *)
Total[Select[Range[2, 999999], Function[x, Total[Map[Function[y, y^5], IntegerDigits[x]]] == x]]]
