(* 
 * Solution to Project Euler problem 29
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* 
 * We generate all possible powers in the given range, put the values in a flat list,
 * delete the duplicates of any value, and count the length of the remaining list.
 *)
Length[Union[Flatten[Table[a^b, {a, 2, 100}, {b, 2, 100}]]]]
