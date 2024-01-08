package week6.sol43105;

class 김유빈_정수삼각형 {

    public int solution(int[][] triangle) {

        int result = 0;
        int weight = 0;

        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {

                if (j == 0) {
                    weight = triangle[i - 1][j];
                } else if (j == triangle[i].length - 1) {
                    weight = triangle[i - 1][j - 1];
                } else {
                    weight = Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
                }

                triangle[i][j] += weight;

                if (i == triangle.length - 1) {
                    result = Math.max(result, triangle[i][j]);
                }
            }
        }

        return result;
    }
}
