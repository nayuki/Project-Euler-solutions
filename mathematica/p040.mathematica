(* 
 * Solution to Project Euler problem 40
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


str = Apply[Join, Table[IntegerDigits[n], {n, 10^6}]];
Product[str[[10^i]], {i, 0, 6}]
