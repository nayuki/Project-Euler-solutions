(* 
 * Solution to Project Euler problem 17
 * by Project Nayuki
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


Ones[n_] := {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"}[[n + 1]]
Tens[n_] := If[n < 10, Ones[n],
  If[n < 20, {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"}[[n - 9]],
  {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"}[[Floor[n / 10] - 1]] <> If[Mod[n, 10] != 0, Ones[Mod[n, 10]], ""]]]
ToEnglish[n_] := If[n < 100, Tens[n],
  If[n >= 1000, Tens[Floor[n / 1000]] <> "thousand", ""] <>
  If[Mod[Floor[n / 100], 10] != 0, Ones[Mod[Floor[n / 100], 10]] <> "hundred", ""] <>
  If[Mod[n, 100] != 0, "and" <> Tens[Mod[n, 100]], ""]]

Sum[StringLength[ToEnglish[n]], {n, 1000}]
