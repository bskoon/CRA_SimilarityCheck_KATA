public class SimilarityChecker {

    public static final double MAX_SCORE_LENGTH = 60.0;

    public double getLengthScore(String firstString, String secondString) {
        assertIllegalArgument(firstString, secondString);

        int firstStringLen = firstString.length();
        int secondStringLen = secondString.length();

        int A = Math.max(firstStringLen, secondStringLen);
        int B = Math.min(firstStringLen, secondStringLen);

        if (checkOverDoubleLength(A, B)) {
            return 0.0;
        }

        return lengthScore(A, B);
    }

    private double lengthScore(int A, int B) {
        double diffRatio = (double) (A - B) / B;
        return (1.0 - diffRatio) * MAX_SCORE_LENGTH;
    }

    private static boolean checkOverDoubleLength(int largerLen, int smallerLen) {
        if (largerLen >= smallerLen * 2) return true;
        return false;
    }

    private void assertIllegalArgument(String firstString, String secondString) {
        if (isNullString(firstString, secondString)) {
            throw new IllegalArgumentException();
        }
        if (isStringLengthZero(firstString, secondString)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isNullString(String firstString, String secondString) {
        return firstString == null || secondString == null;
    }

    private boolean isStringLengthZero(String firstString, String secondString) {
        return firstString.length() == 0 || secondString.length() == 0;
    }

    private boolean isUpperCase(String firstString, String secondString) {
        for (int idx = 0; idx < firstString.length(); idx++) {
            if (firstString.charAt(idx) < 'A' || firstString.charAt(idx) > 'Z') {
                return false;
            }
        }
        for (int idx = 0; idx < secondString.length(); idx++) {
            if (secondString.charAt(idx) < 'A' || secondString.charAt(idx) > 'Z') {
                return false;
            }
        }
        return true;
    }
}
