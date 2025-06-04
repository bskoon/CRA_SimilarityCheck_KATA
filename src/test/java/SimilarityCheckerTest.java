import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityCheckerTest {
    @Test
    void lengthSame() {
        SimilarityChecker similarityChecker = new SimilarityChecker();
        int lenScore = similarityChecker.getLengthScore("ASD", "DSA");
        assertEquals(60, lenScore);
    }
    @Test
    void lengthOverTwice() {
        SimilarityChecker similarityChecker = new SimilarityChecker();
        int lenScore = similarityChecker.getLengthScore("A", "BB");
        assertEquals(0, lenScore);
    }
}