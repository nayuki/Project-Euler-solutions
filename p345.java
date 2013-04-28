/* 
 * Solution to Project Euler problem 345
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p345 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p345().run());
	}
	
	
	public String run() {
		maxSum = new int[MATRIX.length][1 << MATRIX[0].length];
		return Integer.toString(findMaximumSum(0, (1 << MATRIX[0].length) - 1));
	}
	
	
	private int[][] maxSum;  // Memoization
	
	private int findMaximumSum(int startRow, int setOfCols) {
		if (startRow == MATRIX.length) {
			if (setOfCols == 0)
				return 0;
			else
				throw new IllegalArgumentException();
		}
		
		if (maxSum[startRow][setOfCols] == 0) {
			int max = 0;
			for (int col = 0; (1 << col) <= setOfCols; col++) {
				if ((setOfCols & (1 << col)) != 0)
					max = Math.max(MATRIX[startRow][col] + findMaximumSum(startRow + 1, setOfCols ^ (1 << col)), max);
			}
			maxSum[startRow][setOfCols] = max;
		}
		return maxSum[startRow][setOfCols];
	}
	
	
	private static int[][] MATRIX = {
		{  7,  53, 183, 439, 863, 497, 383, 563,  79, 973, 287,  63, 343, 169, 583},
		{627, 343, 773, 959, 943, 767, 473, 103, 699, 303, 957, 703, 583, 639, 913},
		{447, 283, 463,  29,  23, 487, 463, 993, 119, 883, 327, 493, 423, 159, 743},
		{217, 623,   3, 399, 853, 407, 103, 983,  89, 463, 290, 516, 212, 462, 350},
		{960, 376, 682, 962, 300, 780, 486, 502, 912, 800, 250, 346, 172, 812, 350},
		{870, 456, 192, 162, 593, 473, 915,  45, 989, 873, 823, 965, 425, 329, 803},
		{973, 965, 905, 919, 133, 673, 665, 235, 509, 613, 673, 815, 165, 992, 326},
		{322, 148, 972, 962, 286, 255, 941, 541, 265, 323, 925, 281, 601,  95, 973},
		{445, 721,  11, 525, 473,  65, 511, 164, 138, 672,  18, 428, 154, 448, 848},
		{414, 456, 310, 312, 798, 104, 566, 520, 302, 248, 694, 976, 430, 392, 198},
		{184, 829, 373, 181, 631, 101, 969, 613, 840, 740, 778, 458, 284, 760, 390},
		{821, 461, 843, 513,  17, 901, 711, 993, 293, 157, 274,  94, 192, 156, 574},
		{ 34, 124,   4, 878, 450, 476, 712, 914, 838, 669, 875, 299, 823, 329, 699},
		{815, 559, 813, 459, 522, 788, 168, 586, 966, 232, 308, 833, 251, 631, 107},
		{813, 883, 451, 509, 615,  77, 281, 613, 459, 205, 380, 274, 302,  35, 805},
	};
	
}
