(* 
 * Solution to Project Euler problem 65
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 *)


Total[IntegerDigits[Numerator[FromContinuedFraction[ContinuedFraction[E, 100]]]]]
