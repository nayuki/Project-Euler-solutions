(* 
 * Solution to Project Euler problem 121
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


(* 
 * At the beginning of turn number k (0-based), there are k + 2 discs to choose from.
 * Hence a game that has n turns has (n+1) * n * ... * 1 = (n + 1)! outcomes.
 * 
 * Let f(k, b) be the number of ways to accumulate exactly b blue discs after k turns.
 * We can see that:
 * - f(0, 0) = 1.
 * - f(0, b) = 0, for b > 0.
 * - f(k, 0) = k * f(k - 1, 0), for k > 0.
 *   (Add a red disc, where there are k ways)
 * - f(k, b) = f(k - 1, b - 1) + k * f(k - 1, b), for k > 0, b > 0.
 *   (Add a blue disc (1 way) or add a red disc (k ways))
 * 
 * Next, we calculate the sum f(n, j) + f(n, j+1) + ... + f(n, n),
 * where j is the smallest number of blue discs accumulated that exceeds
 * the number of red discs accumulated (which is n - j). So j = ceil((n + 1) / 2).
 * 
 * Finally, the probability of winning is that sum divided by (n + 1)!.
 * For any game where the cost of playing is 1 and the probability of winning is p,
 * the maximum sustainable prize is 1 / p, therefore the maximum sustainable integer prize is floor(1 / p).
 *)
turns = 15;
Ways[0, 0] = 1;
Ways[0, _] = 0;
Ways[k_, b_] := k * Ways[k - 1, b] + Ways[k - 1, b - 1]
Floor[(turns + 1)! / Sum[Ways[turns, i], {i, Ceiling[(turns + 1) / 2], turns}]]
