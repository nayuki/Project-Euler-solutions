#include <stdio.h>
#include <stdint.h>
#include <stddef.h>
#include <string.h>
#include <stdlib.h>

// Trivial solution
size_t getSum(size_t max)
{
    size_t retVal = 0;
    for (size_t i = 1; i < max; ++i)
        if (i % 3 == 0 || i % 5 == 0) 
            retVal += i;
    return retVal;
}

// Trick:
// First Get the number of elements divisible by 3, 5, and 15
// 15 because for example 30 is both divible by 3 and 5
// and we are not allowed to count 30 twice.
// Now use the sum rule: sum of integers going from 0 to n is equal to 1/2 * (n * (n+1))
// The total number of multiples of 3 in max is (max - 1) / 3.
// Minus 1, because max itself is not included 
// The sum of all numbers divisible by 3 is then sum of all mutiples of 3 under max.
// Repeat it for 5 and substract the sum of all multiples of 15 to get the result
size_t getSum2(size_t max) 
{
    size_t limit = max - 1;
    size_t numb5 = limit / 5;
    size_t numb3 = limit / 3;
    size_t numb15 = limit / 15;
    return (5 * (numb5 * (numb5 + 1)) + (3 * (numb3 * (numb3 + 1))) - (15 * (numb15 * (numb15 + 1)))) / 2;
}

/*
 * Reading the assembly ouput of getSum() and getSum2(), one might be tricked into thinking that getSum() is faster because it generated 6 assemnly 
 * lines less. It is however very slow because of the branches (from the for loop and the checks if the number is a mutliple of 3 or 5) which would 
 * definitely lead to false branch predictions.
 * When benchmarking, getSum() takes longer and longer, as the while loop increases while getSum2() finishes at a constant time of a couple of milliseconds.
 */
int main(int argc, char** argv)
{
    size_t max = 1000;
    printf("Result: %lu\n", getSum(max));
    printf("Smarter Result: %lu\n", getSum2(max));

    return 0;
}

