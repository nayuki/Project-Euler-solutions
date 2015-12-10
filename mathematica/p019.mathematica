(* 
 * Solution to Project Euler problem 19
 * by Project Nayuki
 * 
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


DayOfWeek[year_, month_, day_] := Block[
  {m1 = Mod[month - 3, 4800], y = Mod[year + Floor[m1 / 12], 400], m = Mod[m1, 12]},
  Mod[y + Floor[y / 4] - Floor[y / 100] + Floor[(13 * m + 2) / 5] + day + 2, 7]]
Sum[Boole[DayOfWeek[y, m, 1] == 0], {y, 1901, 2000}, {m, 1, 12}]
