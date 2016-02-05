(* 
 * Solution to Project Euler problem 243
 * by Project Nayuki
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


target = 15499/94744;
i = 1;
d = Null;
numer = 1;
denom = 1;
While[d == Null,
  p = Prime[i];
  numer *= p - 1;
  denom *= p;
  If[numer/denom < target,
    For[j = 1, j <= p, j++,
      If[(numer * j) / (denom * j - 1) < target,
        d = denom * j;
        Break[]]]]
  i++]
d
