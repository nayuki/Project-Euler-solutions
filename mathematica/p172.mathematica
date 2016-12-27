(* 
 * Solution to Project Euler problem 172
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


len = 18;
maxCount = 3;
base = 10;

PartitionAndCount[sum_, max_, terms_] := If[Length[terms] == base,
  If[sum == 0, CountWays[terms], 0],
  Total[Table[PartitionAndCount[sum - i, i, Append[terms, i]], {i, 0, Min[max, sum]}]]]
CountWays[terms_] := Apply[Multinomial, Table[Count[terms, i], {i, 0, maxCount}]] * Apply[Multinomial, terms]

PartitionAndCount[len, maxCount, {}] * (base - 1) / base
