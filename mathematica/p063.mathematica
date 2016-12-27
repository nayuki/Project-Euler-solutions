(* 
 * Solution to Project Euler problem 63
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* 
 * Let's examine n^k for different values of n and k and see which
 * choices cannot possibly work (i.e. not being exactly k digits long).
 * 
 * When k = 22, 9^22 has 21 digits but 10^22 has 23 digits.
 * Furthermore, for n < 9, n^22 has at most 21 digits; for n > 10,
 * n^22 has at least 23 digits. So there are no solutions when k = 22.
 * 
 * The argument extends upwards. When k > 22, 9^k still has less than k digits,
 * and 10^k still has more than k digits. Thus we should only consider 1 <= k <= 21.
 * 
 * For a given digit length k, the largest number is 9...9 (i.e. 10^k - 1)
 * and the smallest number is 10...0 (i.e. 10^(k-1)).
 * The largest k'th power within this range is floor(10^k - 1)
 * and the smallest k'th power within the range is ceiling(10^(k-1)).
 * Therefore the number of k'th powers is
 * floor((10^k - 1)^(1/k)) - ceiling((10^(k-1))^(1/k)) + 1.
 * Finally, we sum this over all valid choices of k.
 *)
Sum[Floor[(10^k - 1)^(1/k)] - Ceiling[(10^(k-1))^(1/k)] + 1, {k, 21}]
