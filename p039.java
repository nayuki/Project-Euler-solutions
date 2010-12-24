public class p039 {
	
	public static void main(String[] args) {
		int maxP = -1;
		int maxTriangles = -1;
		for (int p = 0; p <= 1000; p++) {
			int triangles = 0;
			for (int a = 0; a <= 1000; a++) {
				for (int b = 0; b <= 1000; b++) {
					int c = p - a - b;
					if (a >= b && b >= c && c > 0) {
						if (b * b + c * c == a * a)
							triangles++;
					}
				}
			}
			if (maxTriangles == -1 || triangles > maxTriangles) {
				maxTriangles = triangles;
				maxP = p;
			}
		}
		System.out.println(maxP);
	}
	
}
