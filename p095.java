/* 
 * Solution to Project Euler problem 95
 * By Nayuki Minase
 */


public class p095 {
	
	public static void main(String[] args) {
		int n = 1000001;
		int[] divisorSum = new int[n];
		for (int i = 1; i < n; i++) {
			for (int j = i * 2; j < n; j += i)
				divisorSum[j] += i;
		}
		
		int[] amicableChainLen = new int[n];
		for (int i = 0; i < n; i++) {
			int count = 1;
			boolean[] visited = new boolean[n];
			visited[i] = true;
			int temp = i;
			while (true) {
				int next = divisorSum[temp];
				if (next == i) {
					amicableChainLen[i] = count;
					break;
				} else if (next >= n || visited[next]) {
					amicableChainLen[i] = 0;  // Illegal
					break;
				} else {
					visited[next] = true;
					temp = next;
				}
				count++;
			}
		}
		
		int maxChainLen = 0;
		for (int x : amicableChainLen)
			maxChainLen = Math.max(x, maxChainLen);
		
		for (int i = 0; i < n; i++) {
			if (amicableChainLen[i] == maxChainLen) {
				System.out.println(i);
				return;
			}
		}
		throw new AssertionError();
	}
	
}
