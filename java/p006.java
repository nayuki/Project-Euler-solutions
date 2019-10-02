/* 
 * Solution to Project Euler problem 6
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p006 implements EulerSolution {
	
	public static void main(String[] args)
    {
        int sumOfSquares = 0;
        int sum = 0;
        for (int i = 1; i <= 100; i++)
        {
            int square = i*i;
            sumOfSquares += square; 
        }
        for (int i = 1; i <= 100; i++)
        {
            sum += i;
        }

        int total = sum*sum;
        System.out.println(total -= sumOfSquares);
    }
	
}
