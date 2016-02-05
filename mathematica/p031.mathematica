(* 
 * Solution to Project Euler problem 31
 * by Project Nayuki
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


Coins = {1, 2, 5, 10, 20, 50, 100, 200};
Ways[coinIndex_, total_] := Ways[coinIndex, total] =  (* Memoization *)
  If[coinIndex == 0, Boole[total == 0],
    Sum[Ways[coinIndex - 1, total - i * Coins[[coinIndex]]], {i, 0, Floor[total / Coins[[coinIndex]]]}]]
Ways[Length[Coins], 200]
