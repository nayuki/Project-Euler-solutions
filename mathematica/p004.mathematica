(* 
 * Solution to Project Euler problem 4
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Computers are fast, so we can implement this solution directly without any clever math. *)
PalindromeQ[x_] := IntegerDigits[x] == Reverse[IntegerDigits[x]]
Max[Select[Flatten[Table[i * j, {i, 100, 999}, {j, 100, 999}]], PalindromeQ]]
