(* 
 * Solution to Project Euler problem 56
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


Max[Flatten[Table[Total[IntegerDigits[a^b]], {a, 100}, {b, 100}]]]
