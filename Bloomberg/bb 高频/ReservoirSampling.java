import java.util.Random;

public class ReservoirSampling {
	// A function to select k items from stream[0...n-1] with equal possibility
	public static int[] selectKItems(int stream[], int n, int k) {
		//1. Index for elements in stream[]
		int i = 0;
		//2. Init reservoir[k]
		int[] reservoir = new int[k];
		//3. Copy the first k elements
		for( ; i < k; i++) 
			reservoir[i] = stream[i];
		//4. Iterate from (k+1)th to nth element
		Random r = new Random();
		for( ; i < n; i++) {
			//5. Pick a random index from 0 to i
			int j = r.nextInt(i + 1);
			//6. If j is smaller than k, replace it with stream[i]
			if(j < k)
				reservoir[j] = stream[i];
		}
		return reservoir;
	}
}
