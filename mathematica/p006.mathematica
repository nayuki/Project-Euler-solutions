(* 
 * Solution to Project Euler problem 6
 * by Project Nayuki
 * 
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* 
 * For the mathematically inclined:
 *   Sum[k  , {k, n}] = n(n + 1) / 2.
 *   Sum[k^2, {k, n}] = n(n + 1)(2n + 1) / 6.
 *)
n = 100;
Sum[k, {k, n}]^2 - Sum[k^2, {k, n}]
