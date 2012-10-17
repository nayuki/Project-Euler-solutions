/* 
 * Solution to Project Euler problem 214
 * By Nayuki Minase
 * 
 * http://nayuki.eigenstate.org/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public class p214 {
	
	public static void main(String[] args) {
		int n = 40000000;
		int[] totient = Library.listTotients(n);
		
		int[] totientChainLength = new int[n + 1];
		totientChainLength[0] = 0;
		for (int i = 1; i < totientChainLength.length; i++) {
			totientChainLength[i] = totientChainLength[totient[i]] + 1;
		}
		
		long sum = 0;
		for (int i = 0; i < n; i++) {
			if (totient[i] == i - 1 && totientChainLength[i] == 25)
				sum += i;
		}
		System.out.println(sum);
	}
	
}
