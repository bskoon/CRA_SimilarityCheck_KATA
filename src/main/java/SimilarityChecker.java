import java.nio.file.attribute.UserPrincipalNotFoundException;

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
        int shorterLength = 0;

        if (firstStringLen <= secondStringLen) {
            shorterLength = firstStringLen;
        } else {
            shorterLength = secondStringLen;
        }

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
        if (firstString == null || secondString == null) {
            throw new IllegalArgumentException();
        }
        if (firstString.length() == 0 || secondString.length() == 0) {
            throw new IllegalArgumentException();
        }
    }
}
