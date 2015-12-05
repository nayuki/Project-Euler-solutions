(* 
 * Solution to Project Euler problem 188
 * by Project Nayuki
 * 
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Use this in Wolfram Mathematica *)

$RecursionLimit = 2000;
TetrationMod[x_, 1 , m_] := Mod[x, m]
TetrationMod[x_, y_, m_] := PowerMod[x, TetrationMod[x, y - 1, EulerPhi[m]], m]
TetrationMod[1777, 1855, 10^8]
