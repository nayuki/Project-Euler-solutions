(* 
 * Solution to Project Euler problem 19
 * by Project Nayuki
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


<< Miscellaneous`Calendar`
Sum[Boole[DayOfWeek[{y, m, 1}] === Sunday], {y, 1901, 2000}, {m, 1, 12}]
