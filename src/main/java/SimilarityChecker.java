import java.util.HashMap;
import java.util.Spliterator;

abstract class SubScore {
    protected static final double MAX_SCORE = 0.0;

    public double getScore(String str1, String str2) {
        return MAX_SCORE;
    }

    protected void assertIllegalArgument(String firstString, String secondString) {
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

    protected boolean isUpperCase(String firstString, String secondString) {
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

class LengthScore extends SubScore {
    private static final double MAX_SCORE = 60.0;

    @Override
    public double getScore(String str1, String str2) {
        assertIllegalArgument(str1, str2);

        int firstStringLen = str1.length();
        int secondStringLen = str2.length();

        int A = Math.max(firstStringLen, secondStringLen);
        int B = Math.min(firstStringLen, secondStringLen);

        if (checkOverDoubleLength(A, B)) {
            return 0.0;
        }

        return lengthScore(A, B);
    }

    private double lengthScore(int A, int B) {
        double gap = A - B;
        return (1.0 - gap / B) * MAX_SCORE;
    }

    private boolean checkOverDoubleLength(int A, int B) {
        return A >= B * 2;
    }
}

class AlphaScore extends SubScore {
    private static final double MAX_SCORE = 40.0;

    @Override
    public double getScore(String firstString, String secondString) {
        assertIllegalArgument(firstString, secondString);
        if (!isUpperCase(firstString, secondString)) {
            throw new IllegalArgumentException();
        }

        HashMap<Character, Integer> counterA = countAlpha(firstString);
        HashMap<Character, Integer> counterB = countAlpha(secondString);

        int sameCount = getSameCount(counterA, counterB);
        int totalCount = getTotalCount(counterA, counterB);

        return MAX_SCORE * sameCount / totalCount;
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
}

public class SimilarityChecker {
    LengthScore lenScore = new LengthScore();
    AlphaScore alphaScore = new AlphaScore();


    public double getScore(String firstString, String secondString) {
        return lenScore.getScore(firstString, secondString)
                + alphaScore.getScore(firstString, secondString);
    }
}







