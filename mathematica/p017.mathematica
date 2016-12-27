(* 
 * Solution to Project Euler problem 17
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* 
 * - For the numbers 0 to 19, we write the single word:
 *   {zero, one, two, three, four, five, six, seven, eight, nine,
 *   ten, eleven, twelve, thirteen, fourteen, fifteen, sixteen, seventeen, eighteen, nineteen}.
 * - For the numbers 20 to 99, we write the word for the tens place:
 *   {twenty, thirty, forty, fifty, sixty, seventy, eighty, ninety}.
 *   Subsequently if the last digit is not 0, then we write the word for the ones place (one to nine).
 * - For the numbers 100 to 999, we write the ones word for the hundreds place followed by "hundred":
 *   {one hundred, two hundred, three hundred, ..., eight hundred, nine hundred}.
 *   Subsequently if the last two digits are not 00, then we write the word "and"
 *   followed by the phrase for the last two digits (from 01 to 99).
 * - For the numbers 1000 to 999999, we write the word for the three digits starting at the
 *   thousands place and going leftward, followed by "thousand". Subsequently if the last three
 *   digits are not 000, then we write the phrase for the last three digits (from 001 to 999).
 *)

ones = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
        "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
tens = {"", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

ToEnglish[n_] := Piecewise[{
  {"zero",
    n == 0},
  {ones[[n]],
    1 <= n < 20},
  {tens[[Floor[n / 10]]] <> If[Mod[n, 10] != 0, ones[[Mod[n, 10]]], ""],
    20 <= n < 100},
  {ones[[Floor[n / 100]]] <> "hundred" <> If[Mod[n, 100] != 0, "and" <> ToEnglish[Mod[n, 100]], ""],
    100 <= n < 1000},
  {ToEnglish[Floor[n / 1000]] <> "thousand" <> If[Mod[n, 1000] != 0, ToEnglish[Mod[n, 1000]], ""],
    1000 <= n < 1000000}}]

Sum[StringLength[ToEnglish[n]], {n, 1000}]
