# 
# Test suite for Project Euler all Python solutions
# by Project Nayuki
# 
# http://www.nayuki.io/page/project-euler-solutions
# https://github.com/nayuki/Project-Euler-solutions
# 

import importlib, time


def main():
	totaltime = 0.0
	for (prob, expectans) in sorted(ANSWERS.items()):
		module = importlib.import_module("p{:03d}".format(prob))
		starttime = time.time()
		actualans = module.compute()
		elapsedtime = time.time() - starttime
		totaltime += elapsedtime
		print("Problem {:03d}: {:7d} ms{}".format(prob, round(elapsedtime * 1000), "" if actualans == expectans else "    *** FAIL ***"))
	print("Total computation time: {} ms".format(round(totaltime * 1000)))


ANSWERS = {
	  1: "233168",
	  2: "4613732",
	  3: "6857",
	  4: "906609",
	  5: "232792560",
	  6: "25164150",
	  7: "104743",
	  8: "23514624000",
	  9: "31875000",
	 10: "142913828922",
	 11: "70600674",
	 12: "76576500",
	 13: "5537376230",
	 14: "837799",
	 15: "137846528820",
	 16: "1366",
	 17: "21124",
	 18: "1074",
	 19: "171",
	 20: "648",
	 21: "31626",
	 22: "871198282",
	 23: "4179871",
	 24: "2783915460",
	 25: "4782",
	 26: "983",
	 27: "-59231",
	 28: "669171001",
	 29: "9183",
	 30: "443839",
	 31: "73682",
	 32: "45228",
	 33: "100",
	 34: "40730",
	 35: "55",
	 36: "872187",
	 37: "748317",
	 38: "932718654",
	 39: "840",
	 40: "210",
	 41: "7652413",
	 42: "162",
	 43: "16695334890",
	 45: "1533776805",
	 46: "5777",
	 47: "134043",
	 48: "9110846700",
	 50: "997651",
	 52: "142857",
	 53: "4075",
	 55: "249",
	 56: "972",
	 57: "153",
	 58: "26241",
	 63: "49",
	 65: "272",
	 67: "7273",
	 69: "510510",
	 70: "8319823",
	 71: "428570",
	 72: "303963552391",
	 76: "190569291",
	 77: "71",
	 80: "40886",
	 81: "427337",
	 85: "2772",
}


if __name__ == "__main__":
	main()
