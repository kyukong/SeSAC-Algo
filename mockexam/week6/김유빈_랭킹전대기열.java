package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 김유빈_랭킹전대기열 {

    static class Player implements Comparable {

        int level;
        String nickname;

        public Player(int level, String nickname) {
            this.level = level;
            this.nickname = nickname;
        }

        @Override
        public String toString() {
            return level + " " + nickname;
        }

        @Override
        public int compareTo(final Object o) {
            Player other = (Player) o;

            int length1 = nickname.length();
            int length2 = other.nickname.length();
            int min = Math.min(length1, length2);

            for (int i = 0; i < min; i++) {
                char c1 = nickname.charAt(i);
                char c2 = other.nickname.charAt(i);
                if (c1 != c2) {
                    return c1 - c2;
                }
            }
            return length1 - length2;
        }
    }

    static class Room implements Comparable<Room> {

        int no;
        int level;
        Queue<Player> players = new PriorityQueue<>();

        public Room(final int no, final int level) {
            this.no = no;
            this.level = level;
        }

        @Override
        public int compareTo(final Room other) {
            return no - other.no;
        }

        @Override
        public String toString() {
            return "Room{" +
                "no=" + no +
                ", level=" + level +
                ", players=" + players +
                '}';
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            final Room room = (Room)o;
            return no == room.no;
        }

        @Override
        public int hashCode() {
            return Objects.hash(no);
        }
    }

    private static int p;
    private static int m;
    private static int no = 0;
    private static List<Room> rooms = new ArrayList<>();

    /*
    - 사전순 정렬
    3 3
    10 aaaa
    15 aan
    10 aaaaaaa

    answer
    Started!
    10 aaaa
    10 aaaaaaa
    15 aan
     */

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        p = Integer.parseInt(st.nextToken()); // 플레이어 수
        m = Integer.parseInt(st.nextToken()); // 방의 정원

        // 입력
        int level;
        String nickname;
        Room room;
        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());

            level = Integer.parseInt(st.nextToken());
            nickname = st.nextToken();
            room = getRooms(level);

            if (room == null) {
                room = new Room(++no, level);
                rooms.add(room);
            }
            room.players.add(new Player(level, nickname));
        }

        // 출력
        for (Room createdRoom : rooms) {
            if (createdRoom.players.size() >= m) {
                sb.append("Started!").append("\n");
            } else {
                sb.append("Waiting!").append("\n");
            }

            while (!createdRoom.players.isEmpty()) {
                Player player = createdRoom.players.poll();
                sb.append(player).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static Room getRooms(int level) {
        // 1. 인원이 남아있으면서 2. 레벨이 충족되는 경우 3. 가장 먼저 생성된 방
        return rooms.stream()
            .filter(room -> room.players.size() < m) // 인원이 남아있으면서
            .filter(room -> level - 10 <= room.level && room.level <= level + 10) // 레벨이 충족되는 경우
            .sorted() // 가장 먼저 생성된 방
            .findFirst()
            .orElse(null);
    }
}
