
package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15486 {
	static int N;
	static int[] days;
	static int[] d;
	static int[] profits;
	static int max;
	public static void 김동욱_퇴사2(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		days= new int[N+1]; // T[i]
		profits = new int[N+1]; // P[i]
		max=0;
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			days[i]= Integer.parseInt(st.nextToken());
			profits[i]= Integer.parseInt(st.nextToken());
		}
		
		d = new int[N+2]; // 그 날 얻을 수 있는 최대이득
		
		for(int i=N;i>0;i--) { // N+1 ~ 1 까지
			
			if(days[i]+i<=N+1) { // 해당날짜+ T[i] 가 N을 넘어가지 않는다면
			d[i] = Math.max(d[days[i]+i]+profits[i],max); // 최대 이득 값과 비교
			max=d[i]; // 최대 이득 값 갱신 
			}
			else
				max= d[i]; // 해당날짜+ T[i] 가 N을 넘어간다면 최대 이익 저장
		}
		
		System.out.println(max);
		
	}

}

