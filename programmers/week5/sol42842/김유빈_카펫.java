package week5.sol42842;

class 김유빈_카펫 {

    public int[] solution(int brown, int yellow) {

        int middle = (int) Math.ceil(Math.sqrt(yellow));

        int y, width, height;
        for (int x = middle; x > 0; x--) {
            if (yellow % x != 0) {
                continue;
            }

            y = (int) yellow / x;
            width = Math.max(x, y);
            height = Math.min(x, y);

            if ((width + 2) * (height + 2) == brown + yellow) {
                return new int[] {width + 2, height + 2};
            }
        }

        return new int[] {0, 0};
    }
}
