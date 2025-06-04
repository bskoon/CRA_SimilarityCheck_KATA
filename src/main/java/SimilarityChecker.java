public class SimilarityChecker {
    public int getLengthScore(String firstString, String secondString) {
        int firstStringLen = firstString.length();
        int secondStringLen = secondString.length();

        if (firstStringLen == secondStringLen) {
            return 60;
        } else if (checkOverDoubleLength(firstStringLen, secondStringLen)) {
            return 0;
        }

        return 0;
    }

    private static boolean checkOverDoubleLength(int firstStringLen, int secondStringLen) {
        if (firstStringLen > secondStringLen * 2) return true;
        if (firstStringLen * 2 > secondStringLen) return true;
        return false;
    }
}
