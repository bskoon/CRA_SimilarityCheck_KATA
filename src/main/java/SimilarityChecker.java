public class SimilarityChecker {
    public double getLengthScore(String firstString, String secondString) {
        assertIllegalArgument(firstString, secondString);

        int firstStringLen = firstString.length();
        int secondStringLen = secondString.length();

        if (checkOverDoubleLength(firstStringLen, secondStringLen)) {
            return 0.0;
        }

        return lengthScore(firstStringLen, secondStringLen);
    }

    private double lengthScore(int firstStringLen, int secondStringLen) {
        double diffRatio = gapPerLen(firstStringLen, secondStringLen);
        return (1.0 - diffRatio) * 60.0;
    }

    private double gapPerLen(int firstStringLen, int secondStringLen) {
        int stringLenDiff = Math.abs(firstStringLen - secondStringLen);
        int shorterLength = Math.min(firstStringLen, secondStringLen);

        return (double) stringLenDiff / shorterLength;
    }

    private static boolean checkOverDoubleLength(int firstStringLen, int secondStringLen) {
        int twiceOfFirstLength = 2 * firstStringLen;
        int twiceOfSecondLength = 2 * secondStringLen;

        if (firstStringLen > twiceOfSecondLength) return true;

        if (secondStringLen > twiceOfFirstLength) return true;

        return false;
    }

    private void assertIllegalArgument(String firstString, String secondString) {
        if (isNullString(firstString, secondString)) {
            throw new IllegalArgumentException();
        }
        if (isStringLengthZero(firstString, secondString)) {
            throw new IllegalArgumentException();
        }
        if (!isUpperCase(firstString, secondString)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isStringLengthZero(String firstString, String secondString) {
        return firstString.length() == 0 || secondString.length() == 0;
    }

    private boolean isNullString(String firstString, String secondString) {
        return firstString == null || secondString == null;
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
