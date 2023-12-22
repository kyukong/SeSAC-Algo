import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 김동욱_틱택토 {
    static ArrayList<int[]> arrayList;
    static char[] board;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        arrayList = new ArrayList<>();

        arrayList.add(new int[] { 0, 1, 2 });
        arrayList.add(new int[] { 0, 3, 6 });
        arrayList.add(new int[] { 0, 4, 8 });
        arrayList.add(new int[] { 1, 4, 7 });
        arrayList.add(new int[] { 2, 4, 6 });
        arrayList.add(new int[] { 2, 5, 8 });
        arrayList.add(new int[] { 3, 4, 5 });
        arrayList.add(new int[] { 6, 7, 8 });

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        while (true) {
            String temp = br.readLine();
            if (temp.equals("end"))
                break;

            board = temp.toCharArray(); 
            boolean isO = false;
            boolean isX = false;
            boolean isValid = true;
            int xCount = 0;
            int oCount = 0;

            
            for (int i = 0; i < 9; i++) {
                if (board[i] == 'X') xCount++;
                if (board[i] == 'O') oCount++;
            }

            
            for (int[] i : arrayList) {
                String now = "";
                for (int num : i) {
                    now += board[num];
                }

                if (now.equals("OOO")) {
                    isO = true;
                }
                if (now.equals("XXX")) {
                    isX = true;
                }
            }

            
            if (isO && isX) { // OOO XXX 
                isValid = false;
            } else if (isO && xCount != oCount) { // OOO 인 경우  oCount == xCount
                isValid = false;
            } else if (isX && xCount != oCount + 1) { // xxx 인 경우 xCount == oCount+1
                isValid = false;
            } else if (!isO && !isX && (xCount != 5 || oCount != 4)) { // 그냥 끝난 경우 마지막 상태 체크
                isValid = false;
            }

            
            sb.append(isValid ? "valid\n" : "invalid\n");
        }
        System.out.println(sb);
    }
}
