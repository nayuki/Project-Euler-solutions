(* 
 * Solution to Project Euler problem 1
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Computers are fast, so we can implement this solution directly without any clever math. *)
Total[Select[Range[999], Function[x, Mod[x, 3] == 0 || Mod[x, 5] == 0]]]
