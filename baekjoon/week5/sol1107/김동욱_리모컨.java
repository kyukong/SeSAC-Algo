import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 김동욱_리모컨 {
	static int N;
	static int M;
	static int[] broken;
	static int[] targetArray;
	static String channel;
	static int answer;
	static int curNum;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		int size = Integer.toString(N).length();

		M = Integer.parseInt(br.readLine());
		broken = new int[M];
		answer = getAnswer(100,N);
		sb=  new StringBuilder();
		channel = "100";
		if(M==0) {
			int low  = Math.min(getAnswer(100,N), Integer.toString(N).length());
			System.out.println(low);
			System.exit(0);
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			broken[i] = Integer.parseInt(st.nextToken());
		}
		
		// end input
		// 채널 탐색
		for (int i = 0; i <= 999999; i++) {
			boolean isValid = true;
			int tot = 0;
			curNum = i;
			String temp = Integer.toString(curNum);
			for(int k : broken) {
				if(temp.contains(Integer.toString(k))) {
					isValid = false;
					break;
				}
			}
			
			if(!isValid) continue;
			tot += temp.length();
			if(tot> answer) continue;
			tot+= getAnswer(N,curNum);
			// 누르고 빼는거  vs 빼는거
			
			answer = Math.min(answer, tot);
		}
		System.out.println(answer);
	}

	private static int getAnswer(int num, int curNum) {
		boolean isAdd = true;
		int ans = 0;
		return Math.abs(curNum -num);
	}
}
