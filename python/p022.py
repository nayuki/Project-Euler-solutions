# 
# Solution to Project Euler problem 22
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

"""
A much simplified solution to the problem using files to handle the data
"""

def score(name):             #Computes the sum of all alphabets in the name
    a="0ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    s=0
    for i in name:
        if i.isalpha():
            s+=a.index(i)
    return s    
        
	
"""
Note: Here you should save the downloaded text file within the same directory/folder in which you have saved your code file.
This will let you avoid specifying the complete file in the open() functions argument
"""
import time
start=time.time()
with open("names.txt")as f:           #'f' is the object of the file 
    
    l=sorted(f.read().split(","))     #f.read() is a string which is split into a list and then we sorted the list 
    total_sum=0
    for i in range(len(l)):
        total_sum+=score(l[i])*(i+1)
        
print(time.time()-start)    
print(total_sum)
#time = 0.012729644775390625
#total score = 871198282


