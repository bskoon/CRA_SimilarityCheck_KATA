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
    void createSimilarityChecker() {
        assertNotNull(similarityChecker);
    }

    private void assertIllegalInput(String str1, String str2) {
        try {
            double score = similarityChecker.getLengthScore(str1, str2);
            fail();
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    void checkIllegalParameter() {
        assertIllegalInput(null, "ABC");
        assertIllegalInput("BC", null);
        assertIllegalInput("", "ASD");
        assertIllegalInput("DSA", "");
        assertIllegalInput("asd", "ABC");
        assertIllegalInput("DAC", "AcD");
    }

    @Test
    void lengthSame() {
        assertEquals(60, similarityChecker.getLengthScore("ASD", "DSA"));
    }
    @Test
    void lengthOverTwice() {
        assertEquals(0, similarityChecker.getLengthScore("A", "BB"));
    }

    @Test
    void lengthSubScoreTest() {
        assertEquals((1.0-2.0/3.0)*60.0, similarityChecker.getLengthScore("AAABB", "BAA"));
        assertEquals((1.0-1.0/2.0)*60.0, similarityChecker.getLengthScore("AA", "AAE"));
    }
}