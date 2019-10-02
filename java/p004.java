/* 
 * Solution to Project Euler problem 4
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p004
{
	public static boolean isPalindrome(int number)
    {
        String palindrome1 = number + "";
        for (int i = 0; i < palindrome1.length() / 2; i++)
        {
            if (palindrome1.charAt(i) != palindrome1.charAt(palindrome1.length()-i-1))
                return false;
        }
        return true;
    }

    public static int ReturnPalindrome()
    {   
        int doublePalindrome = 0;
        int b = 0; 
        for (int i = 999; i > 100; i--)
        {
            for (int j = 999; j > 100; j--)
            {
                if (isPalindrome(i*j) && i*j > doublePalindrome)
                    doublePalindrome = i*j;
            }
        }
        return doublePalindrome;
    }

    public static void main(String[] args)
    {
        System.out.println(ReturnPalindrome());
    }
}
