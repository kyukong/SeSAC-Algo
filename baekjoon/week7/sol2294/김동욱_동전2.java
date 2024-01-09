import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class 김동욱_동전2 {
	static int N;
	static int K;	
	static int[] profits;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		profits = new int[N + 1];
		int[] d = new int[K + 1];
		
		for (int i = 1; i <= N; i++) {
			int temp = sc.nextInt();
			profits[i] = temp;

		}
		d[0]=0;
		for(int i=1;i<=K;i++) {
			d[i]= 10001;
		}
		
		for(int i=1;i<=N;i++) {
			for(int j=profits[i];j<=K;j++) {
				d[j] = Math.min(d[j], d[j-profits[i]]+1);
			}
		}
		int answer =  d[K] == 10001 ? -1 : d[K];
		
		System.out.println(answer);
	}

}