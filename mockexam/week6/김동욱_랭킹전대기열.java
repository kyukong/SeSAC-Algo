package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_20006 {
	public static class Player implements Comparable<Player> {
		int level;
		String name;

		public Player() {
		}

		public Player(int level, String name) {
			this.level = level;
			this.name = name;
		}

		@Override
		public String toString() {
			return "Player [level=" + level + ", name=" + name + "]";
		}

		@Override
		public int compareTo(Player o) {
			
			int len1 = this.name.length();
			int len2 = o.name.length();
			
			int m = len1 <= len2 ? len1 : len2;
			for(int a=0;a<m;a++) {
				char c1 = this.name.charAt(a);
				char c2 = o.name.charAt(a);
				if(c1!= c2) {
					return c1-c2;
				}
			}
			return len1-len2;
		}
		
		
	}

	static int P, M;
	static Player[] players;
	static ArrayList<Integer> levelList;
	static ArrayList<PriorityQueue<Player>> rooms;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		P = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		players = new Player[P];
		rooms = new ArrayList<PriorityQueue<Player>>();
		levelList = new ArrayList<>();
		sb = new StringBuilder();
		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());

			players[i] = new Player(Integer.parseInt(st.nextToken()), st.nextToken());
		}
		// simul
		// 맨 처음 방 생성 후 플레이어 배치
		makeRoom(players[0]);

		for (int i = 1; i < P; i++) {
			 findRoom(players[i]);

		}
		
		for(PriorityQueue<Player>pq : rooms) {
			if(pq.size()>=M) {
				sb.append("Started!\n");
			}
			else sb.append("Waiting!\n");
			
			while(!pq.isEmpty()) {
				Player p= pq.poll();
				sb.append(p.level+" "+p.name+"\n");
			}
		}
		System.out.println(sb);
	}
	public static void makeRoom(Player player) {
		PriorityQueue<Player> pq = new PriorityQueue<>();
		pq.add(player);
		rooms.add(pq);
		levelList.add(player.level);
	}
	public static boolean isEnter(int level, int comp) {
		if (comp <= level + 10 && comp >= level - 10)
			return true;
		return false;
	}

	public static void findRoom(Player player) {
		// 추가
		boolean flag = false;
		for (int i = 0; i < levelList.size(); i++) {
			if (isEnter(levelList.get(i), player.level)) {
				if(rooms.get(i).size()>=M) continue;
				flag = true;
				rooms.get(i).add(player);
				break;
			}
		}
		if (!flag) {
			makeRoom(player);
		}
	}
	

}

