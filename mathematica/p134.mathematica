(* 
 * Solution to Project Euler problem 134
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* 
 * Let p and q be the two primes. Let k be the smallest power of 10 that exceeds p.
 * The number that we seek is n = mk + p, where n is divisible by q, and m is minimized.
 * (For example, p = 19, q = 23, k = 100, m = 12, n = 1219.)
 * 
 * Firstly, n = mk + p = 0 mod q. By rearrangement, m = -p k^-1 mod q. (k^-1 exists because q is coprime with 10.)
 * Then of course the smallest m that satisfies the divisibility condition is the one such that 0 <= m < q.
 *)
s = 0;
For[i = 3, Prime[i] <= 10^6, i++,
  Block[{p = Prime[i], q = Prime[i + 1], k = 10^(Floor[Log[10, p]] + 1)},
    s += k * Mod[-p * PowerMod[k, -1, q], q] + p]]
s
