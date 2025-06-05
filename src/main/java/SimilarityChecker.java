import java.util.HashMap;
import java.util.IllformedLocaleException;

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
        double gap = A - B;
        return (1.0 - gap / B) * MAX_SCORE_LENGTH;
    }

    private boolean checkOverDoubleLength(int A, int B) {
        return A >= B * 2;
    }

    public double getAlphaScore(String firstString, String secondString) {
        assertIllegalArgument(firstString, secondString);
        if (!isUpperCase(firstString, secondString)) {
            throw new IllegalArgumentException();
        }

        HashMap<Character, Integer> counterA = countAlpha(firstString);
        HashMap<Character, Integer> counterB = countAlpha(secondString);

        int sameCount = getSameCount(counterA, counterB);
        int totalCount = getTotalCount(counterA, counterB);

        return 40.0 * sameCount / totalCount;
    }

    private int getSameCount(HashMap<Character, Integer> counterA, HashMap<Character, Integer> counterB) {
        int sameCount = 0;
        for (char c = 'A'; c <= 'Z'; c++) {
            int countA = counterA.get(c);
            int countB = counterB.get(c);
            if (countA != 0 && countB != 0) {
                sameCount++;
            }
        }
        return sameCount;
    }

    private int getTotalCount(HashMap<Character, Integer> counterA, HashMap<Character, Integer> counterB) {
        int totalCount = 0;
        for (char c = 'A'; c <= 'Z'; c++) {
            int countA = counterA.get(c);
            int countB = counterB.get(c);
            if (countA != 0 || countB != 0) {
                totalCount++;
            }
        }
        return totalCount;
    }

    private HashMap<Character, Integer> countAlpha(String str) {
        HashMap<Character, Integer> counter = new HashMap<>();

        for (char c = 'A'; c <= 'Z'; c++) {
            counter.put(c, 0);
        }
        for (char c : str.toCharArray()) {
            counter.replace(c, counter.get(c) + 1);
        }

        return counter;
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
