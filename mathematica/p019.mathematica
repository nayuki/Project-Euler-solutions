(* 
 * Solution to Project Euler problem 19
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* 
 * We simply use Mathematica's built-in date library to compute the answer by brute force.
 *)
<< Miscellaneous`Calendar`
Sum[Boole[DayOfWeek[{y, m, 1}] === Sunday], {y, 1901, 2000}, {m, 1, 12}]
