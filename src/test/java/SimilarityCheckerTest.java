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
        int lenScore = similarityChecker.getLengthScore("ASD", "DSA");
        assertEquals(60, lenScore);
    }
    @Test
    void lengthOverTwice() {
        int lenScore = similarityChecker.getLengthScore("A", "BB");
        assertEquals(0, lenScore);
    }
}