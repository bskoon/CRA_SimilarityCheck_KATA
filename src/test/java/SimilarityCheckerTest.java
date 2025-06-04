import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityCheckerTest {
    SimilarityChecker similarityChecker;

    @BeforeEach
    void setUp() {
        similarityChecker = new SimilarityChecker();
    }

    @Test
    void lengthSame() {
        double lenScore = similarityChecker.getLengthScore("ASD", "DSA");
        assertEquals(60, lenScore);
    }
    @Test
    void lengthOverTwice() {
        double lenScore = similarityChecker.getLengthScore("A", "BB");
        assertEquals(0, lenScore);
    }

    @Test
    void lengthSubScoreTest() {
        double lenScore = similarityChecker.getLengthScore("AAABB", "BAA");
        assertEquals((1.0-2.0/3.0)*60.0, lenScore);
    }
}