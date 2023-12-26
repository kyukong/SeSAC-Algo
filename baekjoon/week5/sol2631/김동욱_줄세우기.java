import java.util.Scanner;

public class 김동욱_줄세우기 {
	static int N;
	static int[] arr;
	static int[] d;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		arr = new int[N];
		d = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		int temp =0;
		for (int i = 0; i < N; i++) {
			temp = 0;
			for (int j = N - 1; j >= 0; j--) {
				if (arr[i] > arr[j]) {
				//	System.out.println(temp +" "+ d[j]);
					temp = Math.max(temp, d[j]);
				}
			}
			d[i] = temp+1;
		}
		//System.out.println(Arrays.toString(d));
		int max  = 0;
		for(int i=0;i<N;i++) {
			if(max <d[i]) {
				max = d[i];
			}
		}
		System.out.println(N-max);
	}
}
