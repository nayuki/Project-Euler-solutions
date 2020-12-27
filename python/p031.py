# 
# Solution to Project Euler problem 31
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

def compute():
      from itertools import combinations_with_replacement

      cvs = [1, 2, 5, 10, 20, 50, 100, 200]  # Coins and their values
      TOTAL = 10 # Use 10 coins (for example)
      count = 0 # Solutions (combinations) counter
      tracing = True  # Not necessary but used just for tracing the combinations
      if tracing:
        sol = []  # List with solutions
      lst = sorted(list(combinations_with_replacement(cvs, TOTAL)))
      for i in range(len(lst)):
        if sum(lst[i]) == 200: 
          count += 1
          if tracing:
            sol.append(lst[i])

      print("Total combinations:", count)
      if tracing:
        print("Solutions:", sol)
      '''
      Total solutions: 22
      Solutions: [(1, 1, 1, 1, 1, 5, 20, 20, 50, 100), (1, 1, 1, 2, 5, 10, 10, 20, 50, 100), (1, 1, 1, 2, 5, 20, 20, 50, 50, 50), (1, 1, 2, 2, 2, 2, 20, 20, 50, 100),  ...
                        (10, 10, 10, 20, 20, 20, 20, 20, 20, 50), (20, 20, 20, 20, 20, 20, 20, 20, 20, 20)]      
      '''

if __name__ == "__main__":
    compute()
