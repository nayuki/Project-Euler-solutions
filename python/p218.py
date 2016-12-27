# 
# Solution to Project Euler problem 218
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 


# == Primitive Pythagorean triples ==
# 
# Each valid pair generates a PPT:
# 
#   For each pair of positive integers (s,t) such that s > t, they are coprime, and one of them
#   is odd and one of them is even: Let a = s^2 - t^2 (odd), b = 2st (even), and c = s^2 + t^2 (odd).
#   Then (a,b,c) is a primitive Pythagorean triple (PPT).
# 
#   Proof: The fact that a^2 + b^2 = c^2 can be readily verified. Now for the sake of contradiction,
#   suppose some prime p divides each of {a,b,c} (which would make the Pythagorean triple non-primitive).
#   Since p divides b = 2st and p is prime, p must divide at least one of {2,s,t}. a is odd, so 2 doesn't
#   divide a, so p != 2. Hence p > 2, and p must divide at least one of {s,t}. p divides c = s^2 + t^2.
#   WLOG if p divides s, then p divides the difference c - s^2 = t^2, thus p divides t.
#   Therefore p divides {s,t}, contradicting that {s,t} are coprime.
# 
# Each PPT can be generated from some valid pair:
# 
#   Conversely, for each PPT (a,b,c) there exists an (s,t), satisfying the restrictions above,
#   that generates it using the relations above.
# 
#   Proof: WLOG assume that a is odd (otherwise swap the roles of a and b). Knowing that a^2 + b^2 = c^2
#   and b is even, rearrange it to get (b/2)^2 = (c^2 - a^2)/4 = [(c-a)/2][(c+a)/2]. The claim is that
#   (c-a)/2 and (c+a)/2 are both perfect squares. For any prime p, if it divides both (c-a)/2 and (c+a)/2,
#   then it divides the sum (which is c) and the difference (which is a), so it would also divide c^2 - a^2 = b^2,
#   contradicting the primitiveness. Thus each prime power (i.e. p^k) in the factorization of (b/2)^2 appears
#   in either the factor (c-a)/2 or the factor (c+a)/2. Since (b/2)^2 is a perfect square, each prime power
#   in it is a perfect square, and it would contribute to either (c-a)/2 or (c+a)/2, making both of them
#   perfect squares as well.
# 
#   With this setup, let s = sqrt((c+a)/2) and t = sqrt((c-a)/2), which are both positive integers.
#   It's easy to verify that a = s^2 - t^2, b = 2st, and c = s^2 + t^2. Clearly s > t, since a is added in s
#   while a is subtracted in t. {s,t} cannot both be even or both be odd, otherwise 2 divides all of {a,b,c},
#   contradicting primitiveness. {s,t} must be coprime, otherwise some p divides {s,t}, so p^2 divides s^2 = (c+a)/2
#   and p^2 divides t^2 = (c-a)^2, which contradicts {a,c} being coprime using the argument above.
# 
# 
# == Perfect triangles ==
# 
# A perfect right-angled triangle (a,b,c) has c = r^2 for some integer r. We use the PPT theorem converse
# to find (s,t). The area of the triangle (a,b,c) is ab/2 = (s^2 - t^2)(2st)/2 = st(s^2 - t^2).
# Curiously, we have c = s^2 + t^2 = r^2, which means (s,t,r) is itself a Pythagorean triple, and in fact
# a primitive one because (s,t) are coprime. Use the PPT theorem converse on (s,t,r) (or (t,s,r), depending on
# which of s or t is odd) to find (u,v), i.e. s = u^2 - v^2, t = 2uv, and r = u^2 + v^2.
# So the area is also expressible as (u^2 - v^2)(2uv)[(u^2 - v^2)^2 - (2uv)^2].
# 
# The area is divisible by 6 and 28 (super-perfectness) iff it is divisible by lcm(6, 28) = 84
# = 3 * 4 * 7 (coprime factorization) iff it is divisible by 3, 4, and 7.
# Now, the area is divisible by 4 because in the factor 2uv, either u or v is even.
# The area is divisible by 3 because st is a factor in one of the area formulas, and with (s,t,r) being
# a Pythagorean triple, at least one of {s,t} must be 0 mod 3 (see footnote 0).
# Similarly, since (s,t,r) is a Pythagorean triple, 7 divides at least one of {s,t} (so 7 divides
# the area factor of st), or s^2 = t^2 mod 7 (so 7 divides the area factor s^2 - t^2) (see footnote 1).
# 
# In conclusion, every perfect right-angled triangle is also super-perfect.
# There is no perfect triangle that isn't super-perfect.
# 
# Footnote 0: This can be proven by brute force over the 3^3 cases of values of s,t,r mod 3.
# Or alternatively: If s or t is 0 mod 3, then we're done. Otherwise, s is either 1 or 2 mod 3,
# and t is either 1 or 2 mod 3. s^2 = 1 mod 3, and t^2 = 1 mod 3. s^2 + t^2 = 2 = r^2 mod 3,
# but no r can satisfy r^2 = 2 mod 3. So this "otherwise" case is impossible.
# 
# Footnote 1: This can also be proven by brute force over all 7^3 cases of values of s,t,r mod 7.
# Or alternatively: If s or t is 0 mod 7, then we're done. Otherwise, notice that for k != 0 mod 7,
# we have that k^2 mod 7 is in the set {1,2,4} (quadratic residues). If s^2 != t^2 mod 7,
# then their sum mod 7 is not a residue, so r^2 != s^2 + t^2. Therefore it must be that s^2 = t^2 mod 7
# (e.g. s = 3 mod 7, t = 4, r = 2 mod 7).


def compute():
	return "0"


if __name__ == "__main__":
	print(compute())
