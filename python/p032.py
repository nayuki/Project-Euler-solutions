# 
# Solution to Project Euler problem 32
# Copyright (c) Project Nayuki. All rights reserved.
# 
# https://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

# Note: This code isobviously much longer that the one presented previously as a solution,
#       but the process is "transparent" and, more importantly the results can be examined and verified

def compute():
    # Returns a list with all the divisors of 'n'
    def all_divisors(n):
      return [i for i in range(1,n//2+1) if not n % i]

    # Returns True/False if 'n' contains or not duplicate digits
    def contains_duplicates(n):
      s = str(n)
      for i in range(len(s)):
        if s[i] in s[i+1:]: return True
      return False

    def in_sn(n):   # Check 1) 'n' is pandigital and 2) if its digits are already in global 'sn'
      global sn   # Return True/False if it's/not a new pandigital 
      s = str(n)  # If False, 'sn' will be updated
      sn2 = sn # Use a temporar var
      for i in range(len(s)):
        if s[i] in sn2: return True
        sn2 += s[i] # Update 'sn' (This is only temporary)
      sn = sn2 # Now it can be updated
      return False

    aplist = []  # This is not necessary, but it can be  used for examining the results and confirming their validity
    total = 0    # This will carry total solutions
    for n in range(1, 10000):
      sn = str(n) # Current product to be examinded, as string _ It serves to check digit uniqueness
      if not contains_duplicates(sn):
        if not '0' in str(n):   # Exclude 0's from numbers
          lst = all_divisors(n) # Get all divisors of current number
          plist_s = [] # This is only for viewing/checking each result
          sn_bak = sn  # Keep a backup to restore
          for i in range(1, len(lst)): # Exclude '1' from divisors
            n1 = lst[i]            # Fetch next divisor - This will be the multiplicand of the product
            if not '0' in str(n1): # Exclude 0's
              if not in_sn(n1):    # If not alread in 'sn'
                n2 = int(n/n1)     # Get the remainder from current 'n' - This will be the multiplier of the product
                # 'n2' will be valid only if it lies ahead in the divisors list
                # So we avoid swapping multiplicand with multipliers!
                if not '0' in str(n2) and n2 > n1 and not in_sn(n2):
                  total += 1; aplist.append(str(n1)+"*"+str(n2)+"="+str(n))
            sn = sn_bak # Restore 'sn' for next round
    return total


if __name__ == "__main__":
	print(compute()) # = 187
