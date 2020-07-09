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
    int numb5 = limit / 5;
    int numb3 = limit / 3;
    int numb15 = limit / 15;
    return (5 * (numb5 * (numb5 + 1)) + (3 * (numb3 * (numb3 + 1))) - (15 * (numb15 * (numb15 + 1)))) / 2;

}


/*
 * The funny thing is that the first getSum() generate less assembly code than the getSum2().
 * However when benchmarking, the second function didn't even run because of the lack of branch predictions. This will make it faster
 */
int main(int argc, char** argv)
{
    size_t max = 1000;
    printf("Result: %lu\n", getSum(max));
    printf("Smarter Result: %lu\n", getSum2(max));

    return 0;
}

