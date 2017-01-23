(* 
 * Solution to Project Euler problem 24
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* 
 * We generate all 10! permutations of the sequence (0,1,2,3,4,5,6,7,8,9)
 * into memory, and select the 1 000 000th element (1-based indexing).
 *)
FromDigits[Permutations[Range[0, 9]][[1000000]]]
