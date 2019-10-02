/* 
 * Solution to Project Euler problem 10
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p010 {
	
	public static boolean isPrime(long z)
    {
        if (z < 2) 
            return false;
        else if (z == 2) 
            return true;
        for (int i = 2; i < Math.pow(z, 0.5) + 1; i++) 
        {
            if (z % i == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args)
    {
        long sum = 0;  
        for (int i = 1; i < 2000000; i++)
        {
            if (isPrime(i))
            {
                sum += i;
            }
        }
        System.out.println(sum);
    }
}
