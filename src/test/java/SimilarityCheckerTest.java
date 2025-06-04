import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

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

    static Stream<Arguments> provideIllegalParameter() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(null, "ABC"),
                org.junit.jupiter.params.provider.Arguments.of("BC", null),
                org.junit.jupiter.params.provider.Arguments.of("", "ASD"),
                org.junit.jupiter.params.provider.Arguments.of("DSA", "")
        );
    }

    @ParameterizedTest
    @MethodSource("provideIllegalParameter")
    void checkIllegalParameter(String param1, String param2) {
        assertIllegalInput(param1, param2);
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