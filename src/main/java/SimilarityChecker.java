public class SimilarityChecker {
    public double getLengthScore(String firstString, String secondString) {
        int firstStringLen = firstString.length();
        int secondStringLen = secondString.length();

        if (firstStringLen == secondStringLen) {
            return 60.0;
        } else if (checkOverDoubleLength(firstStringLen, secondStringLen)) {
            return 0.0;
        } else {
            if (firstStringLen > secondStringLen) {
                return (1.0- (double) (firstStringLen - secondStringLen) / secondStringLen) * 60.0;
            }
            else {
                return (1.0- (double) (secondStringLen - firstStringLen) / firstStringLen) * 60.0;
            }
        }
    }

    private static boolean checkOverDoubleLength(int firstStringLen, int secondStringLen) {
        if (firstStringLen > secondStringLen * 2) return true;
        if (firstStringLen * 2 < secondStringLen) return true;
        return false;
    }
}
