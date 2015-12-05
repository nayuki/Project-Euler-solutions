(* 
 * Solution to Project Euler problem 323
 * by Project Nayuki
 * 
 * http://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* Use this in Wolfram Mathematica *)

(* 
 * Suppose that n 32-bit integers have been OR'd together.
 * For any arbitrary digit:
 *   The probability that it is 0 is 1/2^n.
 *   The probability that it is 1 is 1 - 1/2^n.
 * Thus for the entire number:
 *   The probability that all digits are 1 is (1 - 1/2^n)^32.
 *     This is the cumulative probability function that we want.
 *   The probability that some digit is 0 is 1 - (1 - 1/2^n)^32.
 * 
 * The probability distribution function is simply pdf(n) = cdf(n) - cdf(n-1).
 * So the expected value of the index where the number becomes all 1's is
 * sum(n * pdf(n) for n = 0 to infinity).
 *)

cdf[n_] = (1 - (1/2)^n) ^ 32;
pdf[n_] := cdf[n] - cdf[n - 1]
Sum[n * pdf[n], {n, Infinity}]  (* Exact *)
N[%, 11]  (* Rounded *)
