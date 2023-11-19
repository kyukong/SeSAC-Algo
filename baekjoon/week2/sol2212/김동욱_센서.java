package algorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class 김동욱_센서 {
	static int N;
	static int K;
	static int[] sensors;
	static int[] dist; // 
	static int answer =0;
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		sensors= new int[N];
		dist = new int[N-1];
		for(int i=0;i<N;i++) {
			sensors[i] = sc.nextInt();
		} // end input
		
		// 오름차순
		Arrays.sort(sensors);
		
		for(int i=0;i<N-1;i++) {
			dist[i]= Math.abs(sensors[i]-sensors[i+1]);
		}
		// 중복 상관 없음
		
		
		// 내림차순 , for문만 거꾸로 돌리자
		Arrays.sort(dist);
		
		//System.out.println(Arrays.toString(dist));
		// K 개 부터 시작 
		for(int i = N-K-1;i>=0;i--) {
			answer +=dist[i];
		}
		System.out.println(answer);
	}
}
