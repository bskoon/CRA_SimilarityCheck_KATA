public class SimilarityChecker {
    public int getLengthScore(String asd, String das) {
        if (asd.length() == das.length()) {
            return 60;
        } else if (asd.length() > das.length() * 2) {
            return 0;
        } else if (asd.length() * 2 > das.length()) {
            return 0;
        }
        return 0;
    }
}
