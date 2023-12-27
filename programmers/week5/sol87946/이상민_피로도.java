package week5.sol87946;

public class 이상민_피로도 {

    static int answer = 0;
    static int now = 0;
    static int[] com;
    static boolean[] visited;

    public int solution(int k, int[][] dungeons) {
        answer = Integer.MIN_VALUE;
        now = k;

        com = new int[dungeons.length + 1];
        visited = new boolean[dungeons.length +1];

        for(int i=1; i<=dungeons.length; i++){
            dfs(i, dungeons);
        }

        // combi(1, dungeons);
        return answer;
    }

    static void dfs(int x, int[][] dungeons){
        if(visited[x]) return;
        System.out.println(x);

        for(int i=1; i<=dungeons.length; i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(i, dungeons);
                visited[i] = false;
            }
        }

    }

    static void combi(int index, int[][] dungeons){
        if(index == dungeons.length+1){
            int temp = now;
            int count = 0;

            for(int i=1; i<index; i++){
                int a = com[i] -1;
                if(temp >= dungeons[a][0]){
                    count++;
                    temp -= dungeons[a][1];
                }else{
                    break;
                }
            }

            answer = Math.max(answer, count);
            return;
        }

        for(int i=1; i<=dungeons.length; i++){
            if(!visited[i]){
                visited[i] = true;
                com[index] = i;
                combi(index+1, dungeons);
                visited[i] = false;
            }
        }

    }
}
