(* 
 * Solution to Project Euler problem 1
 * by Project Nayuki
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Computers are fast, so we can implement this solution directly without any clever math. *)
Total[Select[Range[999], Function[x, Mod[x, 3] == 0 || Mod[x, 5] == 0]]]

(* Slightly more compact solution using builtin Mathematica functions*)
Total[Select[Range[999], Divisible[#, 3] || Divisible[#, 5] &]]]

(*Another solution using Mathematica pattern matching functionality*)
Total[Cases[Range[0, 999], _?(Divisible[#, 3] || Divisible[#, 5] &)]]
