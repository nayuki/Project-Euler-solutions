(* 
 * Solution to Project Euler problem 104
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


i = 0;
prevfibmod = 1;  (* Loop invariant: Equal to Fibonacci[i - 1] *)
fibmod = 0;  (* Loop invariant: Equal to Fibonacci[i] *)
While[True,
  If[DigitCount[fibmod] == {1, 1, 1, 1, 1, 1, 1, 1, 1, 0} &&
      Sort[Take[IntegerDigits[Fibonacci[i]], 9]] == Range[9],
    Break[]];
  nextfibmod = Mod[prevfibmod + fibmod, 10^9];
  prevfibmod = fibmod;
  fibmod = nextfibmod;
  i++]
i
