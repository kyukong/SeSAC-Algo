package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16434 {
	static int N;
	static long maxHP;
	static long defaultATK;
	static long curHP;
	static long curATK;

	static long[][] dungeon;
	static long[] dNum;
	static long answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		defaultATK = Long.parseLong(st.nextToken());
		answer =0L;
		maxHP = 0L;
		curATK = defaultATK;
		dNum = new long[N];
		dungeon = new long[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			dNum[i] = Integer.parseInt(st.nextToken());
			dungeon[i][0] = Long.parseLong(st.nextToken());
			dungeon[i][1] = Long.parseLong(st.nextToken());

		}
		// simul

		curHP = 0;
		for (int i = 0; i < N; i++) {
			long ATK = dungeon[i][0];
			long HP = dungeon[i][1];
			// 몬스터
			if (dNum[i] == 1) {
				long add = 1;
				long remainders = HP % curATK;
				if(remainders >0) {
					add =0;
				}
				curHP += ATK * ((HP / curATK) - add);
				maxHP = Math.max(curHP, maxHP);
			}
			// 물약
			else {
				curATK += ATK;
				curHP  = Math.max(curHP-HP, 0);
			}
		}
		System.out.println(maxHP+1);

	}
	
	
	// N^2
	public static boolean fight(long HP, long ATK) { // 용사 win true, lose false,
		while (true) {
			// 용사 ->몬
			HP -= curATK;
			if (HP <= 0)
				break;
			// 몬 -> 용사

			curHP -= ATK;
			if (curHP <= 0) {
				System.out.println(" curHP: ");
				maxHP += (HP * ATK) - HP;
				return false;
			}
		}
		return true;
	}
}

