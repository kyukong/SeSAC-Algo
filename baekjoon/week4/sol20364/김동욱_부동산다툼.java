package algorithm;

import java.util.*;
import java.io.*;

public class 김동욱_부동산다툼 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		HashSet<Integer> land = new HashSet<>();

		for (int i = 1; i <= Q; i++) {
			int num = Integer.parseInt(br.readLine());
			int stopNum = 0;
			for (int j = num; j >= 2; j /= 2)
				if (land.contains(j))
					stopNum = j;
			sb.append(stopNum+"\n");
			if (stopNum == 0)
				land.add(num);
		}
		System.out.println(sb);
	}
}